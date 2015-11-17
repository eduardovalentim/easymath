package org.bitbucket.easymath.processor.mathematical.operation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import org.bitbucket.easymath.annotations.NumberType;
import org.bitbucket.easymath.processor.mathematical.operation.operand.Operand;

public class UnaryOperation extends AbstractOperation {

    private Operand operand;
    private String operandDescr;
    
    public UnaryOperation(int id, NumberType type, Operand operand, String operator, String operandDescr) {
        super(id, operator, type);
        this.operand = operand;
        this.operandDescr = operandDescr;
    }

    @Override
    public Deque<Operand> getOperands() {
        return new LinkedList<Operand>( Arrays.asList(operand) );
    }
    
    /**
     * @return the operand
     */
    public Operand getOperand() {
        return operand;
    }
    
    public String getOperandDescr() {
        return operandDescr;
    }
}
