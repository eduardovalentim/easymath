package org.bitbucket.easymath.processor.mathematical.operation;

import java.util.Deque;

import org.bitbucket.easymath.annotations.NumberType;
import org.bitbucket.easymath.processor.mathematical.operation.operand.Operand;

public abstract class AbstractOperation implements Operation {
    
    private String id;
    private String operator;
    private NumberType type;

    public AbstractOperation(String id, String operator, NumberType type) {
        super();
        this.id = id;
        this.operator = operator;
        this.type = type;
    }

    public abstract Deque<Operand> getOperands();
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public NumberType getType() {
        return type;
    }
    
}
