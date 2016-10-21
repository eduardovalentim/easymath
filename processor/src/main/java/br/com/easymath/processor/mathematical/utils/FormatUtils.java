package br.com.easymath.processor.mathematical.utils;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import br.com.easymath.processor.mathematical.operation.operand.InputOperand;

public class FormatUtils {

    public static String formatFormulaInputs(String formula, Collection<InputOperand> inputs, int precision) {
        if (formula == null) {
            throw new IllegalStateException("Argument 'formula' cannot be null.");
        }
        if (inputs == null) {
            throw new IllegalStateException("Argument 'inputs' cannot be null.");
        }
        if (precision < 0) {
            throw new IllegalStateException("Argument 'precision' cannot be less than zero.");
        }

        StringBuilder buffer = new StringBuilder();

        if (!inputs.isEmpty()) {
            int index = 0;
            String template = formula;
            for (InputOperand input : inputs) {
                template = StringUtils.replace(template, input.getValue(), "%" + ++index + "$." + precision + "f");
            }
            
            buffer.append(template);
        }

        return buffer.toString();
    }

    public static String formatFormulaOperation(String formula, String operation, int precision) {
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
