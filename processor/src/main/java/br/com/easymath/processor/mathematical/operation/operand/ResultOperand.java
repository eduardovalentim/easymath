package br.com.easymath.processor.mathematical.operation.operand;

import br.com.easymath.annotations.NumberType;

public class ResultOperand extends AbstractOperand {

    public ResultOperand(NumberType type, String value) {
        super(value, type, value);
    }

}
