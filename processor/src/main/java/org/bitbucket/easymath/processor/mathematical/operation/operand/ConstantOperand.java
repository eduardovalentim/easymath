package org.bitbucket.easymath.processor.mathematical.operation.operand;

import org.bitbucket.easymath.annotations.NumberType;


public class ConstantOperand extends AbstractOperand {

    public ConstantOperand(NumberType type, String value) {
        super(formatName(type, value), type, value);
    }

    private static String formatName(NumberType type, String value) {
        String name = "";
        
        if (type == NumberType.DOUBLE) {
            name = String.format("D%s", value);
        } else {
            name = String.format("BD%s", value);
        }
        
        return name.replace(".", "_");
    }
}
