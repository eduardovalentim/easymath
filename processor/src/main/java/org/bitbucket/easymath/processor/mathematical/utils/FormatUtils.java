package org.bitbucket.easymath.processor.mathematical.utils;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.bitbucket.easymath.processor.mathematical.operation.operand.InputOperand;

public class FormatUtils {

    public static String formatFormulaInputs(String formula, Collection<InputOperand> inputs) {
        StringBuilder buffer = new StringBuilder(formula);
        buffer.append(" with ");

        int argIndex = 1;
        Iterator<InputOperand> inputIterator = inputs.iterator();
        while (inputIterator.hasNext()) {
            buffer.append(inputIterator.next().getValue()).append("=%" + argIndex++ + "$f");
            if (inputIterator.hasNext()) {
                buffer.append(", ");
            }
        }
        buffer.append(".");

        return buffer.toString();
    }

    public static String formatFormulaOperation(String formula, String operation) {
        return StringUtils.replace(formula, operation, "%1$f", 1);
    }
}
