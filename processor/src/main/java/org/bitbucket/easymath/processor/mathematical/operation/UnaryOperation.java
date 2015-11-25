package org.bitbucket.easymath.processor.mathematical.operation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import org.bitbucket.easymath.annotations.NumberType;
import org.bitbucket.easymath.processor.mathematical.operation.operand.Operand;

public class UnaryOperation extends AbstractOperation {

    private Operand operand;
    
    public UnaryOperation(String id, NumberType type, Operand operand, String operator, String text) {
        super(id, operator, type, text);
        this.operand = operand;
    }

    @Override
    public Deque<Operand> getOperands() {
        return new LinkedList<Operand>( Arrays.asList(operand) );
    }
    
    public Operand getOperand() {
        return operand;
    }
    
    @Override
    public String toString() {
        return "UnaryOperation [operator=" + getOperator() + ", operand=" + operand + "]";
    }
}
