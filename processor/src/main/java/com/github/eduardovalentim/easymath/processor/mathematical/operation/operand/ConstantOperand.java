package com.github.eduardovalentim.easymath.processor.mathematical.operation.operand;

import java.math.BigDecimal;

/**
 * The constant operand
 * 
 * @author eduardo.valentim
 */
public class ConstantOperand extends AbstractOperand {

    /**
     * Public constructor
     * @param type The type
     * @param value The value
     */
    public ConstantOperand(String type, String value) {
        super(formatId(type, value), type, value);
    }

    private static String formatId(String type, String value) {
        String id;
        
        if (BigDecimal.class.getCanonicalName().equals(type)) {
        	id = String.format("BD%s", value);
        } else {
        	id = String.format("D%s", value);
        }
        
        return id.replace(".", "_");
    }
}
