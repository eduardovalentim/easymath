package com.github.eduardovalentim.easymath.processor.mathematical.operation.operand;

/**
 * The input operand
 * 
 * @author eduardo.valentim
 */
public class InputOperand extends AbstractOperand {

    /**
     * Public constructor
     * 
     * @param type The type
     * @param value The value
     */
    public InputOperand(String type, String value) {
        super(value, type, value);
    }
}
