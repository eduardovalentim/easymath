package org.bitbucket.easymath.processor.mathematical;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.apache.commons.lang.StringUtils;

public class FunctionErrorListener extends BaseErrorListener implements ANTLRErrorListener {

    private static final String NEW_LINE = System.getProperty("line.separator");

    private String classname;

    private String formula;

    public FunctionErrorListener(String classname, String formula) {
        this.classname = classname;
        this.formula = formula;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
            String msg, RecognitionException ex) {

        StringBuilder buffer = new StringBuilder();
        buffer.append("Error compiling function in class ").append(classname).append(NEW_LINE);
        buffer.append("Message: ").append(msg).append(NEW_LINE);
        buffer.append(NEW_LINE);
        buffer.append(formula).append(NEW_LINE);
        buffer.append(StringUtils.repeat("-", charPositionInLine)).append("^").append(NEW_LINE);
        buffer.append(NEW_LINE);

        throw new IllegalArgumentException(buffer.toString(), ex);
    }

}
