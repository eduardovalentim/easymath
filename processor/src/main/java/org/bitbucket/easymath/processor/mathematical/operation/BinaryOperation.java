package org.bitbucket.easymath.processor.mathematical.operation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import org.bitbucket.easymath.annotations.NumberType;
import org.bitbucket.easymath.processor.mathematical.operation.operand.Operand;

public class BinaryOperation extends AbstractOperation {

    private Operand leftOperand;
    private String leftFragment;
    private Operand rightOperand;
    private String rightFragment;
    
    public BinaryOperation(int id, NumberType type, Operand leftOperand, String leftFragment, String operator, Operand rightOperand, String rightFragment) {
        super(id, operator, type);
        this.leftOperand = leftOperand;
        this.leftFragment = leftFragment;
        this.rightOperand = rightOperand;
        this.rightFragment = rightFragment;
    }

    @Override
    public Deque<Operand> getOperands() {
        return new LinkedList<>(Arrays.asList(leftOperand, rightOperand));
    }
    
    /**
     * @return the leftOperand
     */
    public Operand getLeftOperand() {
        return leftOperand;
    }
    
    public String getLeftFragment() {
        return leftFragment;
    }

    /**
     * @return the rightOperand
     */
    public Operand getRightOperand() {
        return rightOperand;
    }
    
    public String getRightFragment() {
        return rightFragment;
    }
}
