package org.bitbucket.easymath.processor.mathematical.operation.operand;

import org.bitbucket.easymath.annotations.NumberType;

public class ResultOperand extends AbstractOperand {

    public ResultOperand(NumberType type, String value) {
        super(value, type, value);
    }

}
