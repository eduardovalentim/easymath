package org.bitbucket.easymath.processor.mathematical.operation;

import java.util.Deque;

import org.bitbucket.easymath.annotations.NumberType;
import org.bitbucket.easymath.processor.mathematical.operation.operand.Operand;

public class FunctionOperation extends AbstractOperation {

    private String name;
    private Deque<Operand> operands;
    
    public FunctionOperation(String id, String name, NumberType type, Deque<Operand> operands, String text) {
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
