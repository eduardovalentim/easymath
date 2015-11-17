package org.bitbucket.easymath.processor.mathematical.operation.operand;

import org.bitbucket.easymath.annotations.NumberType;


public class ConstantOperand extends AbstractOperand {

    public ConstantOperand(NumberType type, String operand) {
        super(type, operand);
    }

    @Override
    public String toString() {
        String name = "";
        
        if (getType() == NumberType.DOUBLE) {
            name = String.format("D%s", getOperand());
        } else {
            name = String.format("BD%s", getOperand());
        }
        
        return name.replace(".", "_");
    }
}
