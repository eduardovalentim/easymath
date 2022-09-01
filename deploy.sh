#!/bin/bash

############################################################
# Functions                                                #
############################################################

function help() {
   # Display Help
   echo "Usage: deploy.sh [OPTION]..."
   echo "options:"
   echo "  -c, --clean    Clean up after a release preparation."
   echo "  -p, --prepare  Prepare for a release in SCM."
   echo "  -P, --perform  Perform a release from SCM."
   echo "  -r, --rollback Rollback a previous release."
   echo "  -h, --help     Display this help and exit."
   echo
   exit 0
}

############################################################
# Main program                                             #
############################################################

POSITIONAL_ARGS=()

while [[ $# -gt 0 ]]; do
  case $1 in
    -c|--clean)
      ARG_CLEAN=yes
      shift # past argument
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

# If all arguments are empty
if [ -z "$ARG_CLEAN" ] && [ -z "$ARG_PREPARE" ] && [ -z "$ARG_PERFORM" ] && [ -z "$ARG_ROLLBACK" ]; then
    help
fi

# If running in a CI server
if [ ! -z "$SSH_GITHUB_PRIVATE_KEY" ]; then
    mkdir -p ~/.ssh
    chmod 700 ~/.ssh

    echo -e $SSH_GITHUB_PRIVATE_KEY > ~/.ssh/github.com
    chmod 600 ~/.ssh/github.com
fi

eval $(ssh-agent -s)
ssh-add ~/.ssh/github.com

_RUN_MAVEN="mvn --batch-mode --settings .circleci/settings.xml --activate-profiles release --errors"

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

eval exec "$_RUN_MAVEN"