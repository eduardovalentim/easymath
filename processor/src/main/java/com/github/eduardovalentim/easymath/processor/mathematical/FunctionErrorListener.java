package com.github.eduardovalentim.easymath.processor.mathematical;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.apache.commons.lang3.StringUtils;

/**
 * A listener for errors in the formula
 * 
 * @author eduardovalentim
 */
public class FunctionErrorListener extends BaseErrorListener implements ANTLRErrorListener {

	private static final String NEW_LINE = System.getProperty("line.separator");

	private String classname;

	private String functionName;

	private String formula;

	/**
	 * Public constructor
	 * 
	 * @param classname The classname
	 * @param functionName The function name
	 * @param formula The formula
	 */
	public FunctionErrorListener(String classname, String functionName, String formula) {
		this.classname = classname;
		this.functionName = functionName;
		this.formula = formula;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException ex) {

		StringBuilder buffer = new StringBuilder();
		buffer.append("Error compiling function in class ").append(classname).append(NEW_LINE);
		buffer.append("Message: ").append(msg).append(NEW_LINE);
		buffer.append(NEW_LINE);
		buffer.append(functionName).append("=").append(formula).append(NEW_LINE);
		buffer.append(StringUtils.repeat("-", functionName.length() + charPositionInLine + 1)).append("^")
				.append(NEW_LINE);
		buffer.append(NEW_LINE);

		throw new IllegalArgumentException(buffer.toString(), ex);
	}

}
