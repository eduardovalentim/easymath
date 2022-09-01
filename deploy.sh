#!/bin/bash

############################################################
## Script created to keep the same behavior between the    #
## developer machine and CI Server                         #
############################################################

############################################################
# Functions                                                #
############################################################

function help() {
   # Display Help
   echo "Usage: deploy.sh [OPTION]..."
   echo "options:"
   echo "  -c, --clean        Clean up after a release preparation."
   echo "  -d, --debug        Produce execution debug output."
   echo "  -g, --gpg-keyname  Produce execution debug output."
   echo "  -p, --prepare      Prepare for a release in SCM."
   echo "  -P, --perform      Perform a release from SCM."
   echo "  -r, --rollback     Rollback a previous release."
   echo "  -h, --help         Display this help and exit."
   echo
   exit 0
}

############################################################
# Argument parser                                          #
############################################################

POSITIONAL_ARGS=()

while [[ $# -gt 0 ]]; do
  case $1 in
    -c|--clean)
      ARG_CLEAN=yes
      shift # past argument
      ;;
    -d|--debug)
      ARG_DEBUG=yes
      shift # past argument
      ;;
    -g|--gpg-keyname)
      ARG_GPG_KEYNAME="$2"
      shift # past argument
      shift # past value
      ;;
    -p|--prepare)
      ARG_PREPARE=yes
      shift # past argument
      ;;
    -P|--perform)
      ARG_PERFORM=yes
      shift # past argument
      ;;
    -r|--rollback)
      ARG_ROLLBACK=yes
      shift # past argument
      ;;
    -h|--help)
      help
      shift # past argument
      ;;
    -*|--*)
      echo "Unknown option $1"
      exit 1
      ;;
    *)
      POSITIONAL_ARGS+=("$1") # save positional arg
      shift # past argument
      ;;
  esac
done

set -- "${POSITIONAL_ARGS[@]}" # restore positional parameters

############################################################
# Main                                                     #
############################################################

# If all principal arguments are empty
if [ -z "$ARG_CLEAN" ] && [ -z "$ARG_PREPARE" ] && [ -z "$ARG_PERFORM" ] && [ -z "$ARG_ROLLBACK" ]; then
    # Print help and exit
    help
fi

gpg --batch --list-secret-keys $ARG_GPG_KEYNAME > /dev/null 2>&1
if [ $? -ne 0 ]; then
    # ---
    # Just for documentation, the content of the environment variable is generated using this command
    # ---
    # gpg --yes --output /tmp/private.pgp --armor --export-secret-key your.username@email &&\
    # cat -e /tmp/private.pgp | sed 's/\$/\\n/g' | tr --delete '\n' | xsel --clipboard &&\
    # rm -f /tmp/private.pgp
    if [ ! -z "$GPG_PRIVATE_KEY" ]; then
        export GPG_TTY=$(tty)
        echo -e $GPG_PRIVATE_KEY | gpg --batch --import
    else
        echo "WARNING"
        echo "WARNING Probably this execution will be aborted with a GPG error due to the lack of the secret key"
        echo "WARNING"
    fi
fi

# If running in a CI server
if [ ! -z "$SSH_PRIVATE_KEY" ]; then
    mkdir -p ~/.ssh
    chmod 700 ~/.ssh

    # ---
    # Just for documentation, the content of the environment variable is generated using this command
    # ---
    # cat -e ~/.ssh/github.com | sed 's/\$/\\n/g' | tr --delete '\n' | xsel --clipboard
    echo -e $SSH_PRIVATE_KEY > ~/.ssh/github.com
    chmod 600 ~/.ssh/github.com

    # Once imported the SSH Key, git doesn't know who is the user trying to commit changes to repository,
    # so let's help git to understand
    git config --global user.email $GIT_USER_EMAIL
    git config --global user.name $GIT_USER_NAME
else
    echo "WARNING"
    echo "WARNING Probably this execution will be aborted with a SSH error due to the lack of the private key"
    echo "WARNING"
fi

eval $(ssh-agent -s)
ssh-add ~/.ssh/github.com

# Bake the maven command line
_RUN_MAVEN="mvn --batch-mode --settings .circleci/settings.xml --activate-profiles release"

if [ ! -z "$ARG_CLEAN" ]; then
    _RUN_MAVEN="$_RUN_MAVEN release:clean"
fi

if [ ! -z "$ARG_PREPARE" ]; then
    _RUN_MAVEN="$_RUN_MAVEN release:prepare"
fi

if [ ! -z "$ARG_PERFORM" ]; then
    _RUN_MAVEN="$_RUN_MAVEN release:perform"
fi

if [ ! -z "$ARG_ROLLBACK" ]; then
    _RUN_MAVEN="$_RUN_MAVEN release:rollback"
fi

if [ ! -z "$ARG_DEBUG" ]; then
    _RUN_MAVEN="$_RUN_MAVEN --debug"
fi

eval exec "$_RUN_MAVEN"