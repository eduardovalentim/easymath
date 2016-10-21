package br.com.easymath.processor.mathematical.operation.operand;

import br.com.easymath.annotations.NumberType;

public class InputOperand extends AbstractOperand {

    public InputOperand(NumberType type, String value) {
        super(value, type, value);
    }

}
