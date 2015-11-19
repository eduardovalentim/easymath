package org.bitbucket.easymath.processor.mathematical.operation.operand;

import org.bitbucket.easymath.annotations.NumberType;

public class InputOperand extends AbstractOperand {

    public InputOperand(NumberType type, String value) {
        super(value, type, value);
    }

}
