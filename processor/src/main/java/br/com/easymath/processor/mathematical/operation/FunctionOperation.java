package br.com.easymath.processor.mathematical.operation;

import java.util.Deque;

import br.com.easymath.processor.mathematical.operation.operand.Operand;

/**
 * @author eduardo.valentim
 */
public class FunctionOperation extends AbstractOperation {

    private String name;
    private Deque<Operand> operands;

    /**
     * 
     * @param id
     * @param name
     * @param type
     * @param operands
     * @param text
     */
    public FunctionOperation(String id, String name, String type, Deque<Operand> operands, String text) {
        super(id, null, type, text);
        this.name = name;
        this.operands = operands;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public Deque<Operand> getOperands() {
        return operands;
    }
    
    @Override
    public String toString() {
        return "FunctionOperation [name=" + name + ", operands={" + operands + "}]";
    }

}
