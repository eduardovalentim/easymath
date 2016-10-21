package br.com.easymath.processor.mathematical.operation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import br.com.easymath.processor.mathematical.operation.operand.Operand;

/**
 * @author eduardo.valentim
 */
public class BinaryOperation extends AbstractOperation {

    private Operand leftOperand;
    private Operand rightOperand;

    /**
     * 
     * @param id
     * @param type
     * @param leftOperand
     * @param operator
     * @param rightOperand
     * @param text
     */
    public BinaryOperation(String id, String type, Operand leftOperand, String operator, Operand rightOperand, String text) {
        super(id, operator, type, text);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deque<Operand> getOperands() {
        return new LinkedList<>(Arrays.asList(leftOperand, rightOperand));
    }

    /**
     * 
     * @return
     */
    public Operand getLeftOperand() {
        return leftOperand;
    }

    /**
     * 
     * @return
     */
    public Operand getRightOperand() {
        return rightOperand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BinaryOperation [left=" + leftOperand + ", operator=" + getOperator() + ", right=" + rightOperand + "]";
    }

}
