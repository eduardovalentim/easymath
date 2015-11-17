package org.bitbucket.easymath.processor.mathematical.operation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import org.bitbucket.easymath.annotations.NumberType;
import org.bitbucket.easymath.processor.mathematical.operation.operand.Operand;

public class BinaryOperation extends AbstractOperation {

    private Operand leftOperand;
    private String leftDescr;
    private Operand rightOperand;
    private String rightDescr;
    
    public BinaryOperation(int id, NumberType type, Operand leftOperand, String leftDescr, String operator, Operand rightOperand, String rightDescr) {
        super(id, operator, type);
        this.leftOperand = leftOperand;
        this.leftDescr = leftDescr;
        this.rightOperand = rightOperand;
        this.rightDescr = rightDescr;
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
    
    public String getLeftDescr() {
        return leftDescr;
    }

    /**
     * @return the rightOperand
     */
    public Operand getRightOperand() {
        return rightOperand;
    }
    
    public String getRightDescr() {
        return rightDescr;
    }
}
