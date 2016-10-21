package br.com.easymath.processor.mathematical.operation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import br.com.easymath.processor.mathematical.operation.operand.Operand;

/**
 * @author eduardo.valentim
 */
public class UnaryOperation extends AbstractOperation {

    private Operand operand;
    
    /**
     * 
     * @param id
     * @param type
     * @param operand
     * @param operator
     * @param text
     */
    public UnaryOperation(String id, String type, Operand operand, String operator, String text) {
        super(id, operator, type, text);
        this.operand = operand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deque<Operand> getOperands() {
        return new LinkedList<Operand>( Arrays.asList(operand) );
    }

    /**
     * @return
     */
    public Operand getOperand() {
        return operand;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UnaryOperation [operator=" + getOperator() + ", operand=" + operand + "]";
    }
}
