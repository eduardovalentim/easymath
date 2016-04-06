package org.bitbucket.easymath.processor.mathematical.operation.operand;

import org.bitbucket.easymath.annotations.NumberType;


public class ConstantOperand extends AbstractOperand {

    public ConstantOperand(NumberType type, String value) {
        super(formatId(type, value), type, value);
    }

    private static String formatId(NumberType type, String value) {
        String id = "";
        
        if (type == NumberType.DOUBLE) {
            id = String.format("D%s", value);
        } else if (type == NumberType.APFLOAT) {
        	id = String.format("AP%s", value);
        } else {
            id = String.format("BD%s", value);
        }
        
        return id.replace(".", "_");
    }
}
