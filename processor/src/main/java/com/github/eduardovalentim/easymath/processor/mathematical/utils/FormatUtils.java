package com.github.eduardovalentim.easymath.processor.mathematical.utils;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.github.eduardovalentim.easymath.processor.mathematical.operation.operand.InputOperand;

/**
 * Utility class to format variables in the velocity template
 * 
 * @author eduardovalentim
 */
public class FormatUtils {

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
	 * @param formula The formula
	 * @param inputs The inputs
	 * @param precision The precision
	 * @return The inputs formated
	 */
    public String inputs(String formula, Collection<InputOperand> inputs, int precision) {
        if (formula == null) {
            throw new IllegalStateException("Argument 'formula' cannot be null.");
        }
        if (inputs == null) {
            throw new IllegalStateException("Argument 'inputs' cannot be null.");
        }
        if (precision < 0) {
            throw new IllegalStateException("Argument 'precision' cannot be less than zero.");
        }

        StringBuilder builder = new StringBuilder();

        if (!inputs.isEmpty()) {
            int index = 0;
            String template = formula;
            for (InputOperand input : inputs) {
                template = StringUtils.replace(template, input.getValue(), "{" + index++ + "}");
            }
            
            builder.append(template);
        }

        return builder.toString();
    }

    /**
     * Format the formula operation
     * 
     * @param formula The formula
     * @param operation The operation
     * @param precision The precision
     * @return The operations formated
     */
    public String operation(String formula, String operation, int precision) {
        if (formula == null) {
            throw new IllegalStateException("Argument 'formula' cannot be null.");
        }
        if (operation == null) {
            throw new IllegalStateException("Argument 'operation' cannot be null.");
        }
        if (precision < 0) {
            throw new IllegalStateException("Argument 'precision' cannot be less than zero.");
        }

        return StringUtils.replace(formula, operation, "%1$." + precision + "f", 1);
    }
}
