package com.github.eduardovalentim.easymath.processor.mathematical.utils;

import static org.apache.commons.lang3.StringUtils.repeat;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.eduardovalentim.easymath.processor.mathematical.operation.BinaryOperation;
import com.github.eduardovalentim.easymath.processor.mathematical.operation.FunctionOperation;
import com.github.eduardovalentim.easymath.processor.mathematical.operation.Operation;
import com.github.eduardovalentim.easymath.processor.mathematical.operation.UnaryOperation;
import com.github.eduardovalentim.easymath.processor.mathematical.operation.operand.InputOperand;

/**
 * Utility class to format variables in the velocity template
 * 
 * @author eduardovalentim
 */
public class FormatUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(FormatUtils.class);

	public static final FormatUtils INSTANCE = new FormatUtils();

	/**
	 * Constructor
	 */
	private FormatUtils() {
		super();
	}

	/**
	 * Format formula inputs
	 * 
	 * @param text      The formula
	 * @param inputs    The inputs
	 * @param precision The precision
	 * @return The inputs formated
	 */
	public String formula(String text, Collection<InputOperand> inputs, int precision) {
		return formula(text, inputs, precision, 3);
	}

	/**
	 * Format formula inputs
	 * 
	 * @param text      The formula
	 * @param inputs    The inputs
	 * @param precision The precision
	 * @return The inputs formated
	 */
	public String formula(String text, Collection<InputOperand> inputs, int precision, int plus) {
		if (text == null) {
			throw new IllegalStateException("Argument 'text' cannot be null.");
		}
		if (inputs == null) {
			throw new IllegalStateException("Argument 'inputs' cannot be null.");
		}
		if (precision < 0) {
			throw new IllegalStateException("Argument 'precision' cannot be less than zero.");
		}

		if (!inputs.isEmpty()) {
			int index = 0;
			for (InputOperand in : inputs) {
				text = text.replaceAll("\\b" + in.getValue() + "\\b", output(index++, precision, plus));
			}
		}

		return text;
	}

	/**
	 * Format formula inputs
	 * 
	 * @param text      The formula
	 * @param inputs    The inputs
	 * @param precision The precision
	 * @return The inputs formated
	 */
	public String operation(String text, Operation operation, int precision) {
		return operation(text, operation, precision, 3);
	}

	/**
	 * Format formula inputs
	 * 
	 * @param text      The formula
	 * @param inputs    The inputs
	 * @param precision The precision
	 * @return The inputs formated
	 */
	public String operation(String text, Operation operation, int precision, int plus) {
		LOGGER.debug("Formatting operation {} with precision {}", text, precision);

		if (text == null) {
			throw new IllegalStateException("Argument 'text' cannot be null.");
		}
		if (operation == null) {
			throw new IllegalStateException("Argument 'operation' cannot be null.");
		}
		if (precision < 0) {
			throw new IllegalStateException("Argument 'precision' cannot be less than zero.");
		}

		int index = 0;
		if (operation instanceof UnaryOperation) {
			text = output(index++, precision, plus);
		} else if (operation instanceof BinaryOperation) {
			text = output(index++, precision, plus);
			text += operation.getOperator();
			text += output(index++, precision, plus);
		} else if (operation instanceof FunctionOperation) {

		}

		return text + " = " + output(index, precision, plus);
	}

	public String output(int index, int precision) {
		return output(index, precision, 3);
	}

	public String output(int index, int precision, int plus) {
		return "{" + index + ", number, #." + repeat('#', precision + plus) + "}";
	}
}
