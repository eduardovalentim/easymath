package br.com.easymath.processor.mathematical.operation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import br.com.easymath.annotations.NumberType;
import br.com.easymath.processor.mathematical.operation.operand.Operand;

public class BinaryOperation extends AbstractOperation {

    private Operand leftOperand;
    private Operand rightOperand;

    public BinaryOperation(String id, NumberType type, Operand leftOperand, String operator, Operand rightOperand, String text) {
        super(id, operator, type, text);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Deque<Operand> getOperands() {
        return new LinkedList<>(Arrays.asList(leftOperand, rightOperand));
    }

    public Operand getLeftOperand() {
        return leftOperand;
    }

    public Operand getRightOperand() {
        return rightOperand;
    }

    @Override
    public String toString() {
        return "BinaryOperation [left=" + leftOperand + ", operator=" + getOperator() + ", right=" + rightOperand + "]";
    }

}
