package org.bitbucket.easymath.processor.mathematical.operation;

import java.util.Deque;

import org.bitbucket.easymath.annotations.NumberType;
import org.bitbucket.easymath.processor.mathematical.operation.operand.Operand;

public class FunctionOperation extends AbstractOperation {

    private String name;
    private Deque<Operand> operands;
    private String fragment;
    
    public FunctionOperation(String id, String name, NumberType type, Deque<Operand> operands, String fragment) {
        super(id, null, type);
        this.name = name;
        this.operands = operands;
        this.fragment = fragment;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public Deque<Operand> getOperands() {
        return operands;
    }
    
    public String getFragment() {
        return fragment;
    }

    @Override
    public String toString() {
        return "FunctionOperation [name=" + name + ", operands={" + operands + "}]";
    }

}
