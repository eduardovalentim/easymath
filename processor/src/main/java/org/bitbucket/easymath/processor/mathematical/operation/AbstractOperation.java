package org.bitbucket.easymath.processor.mathematical.operation;

import java.util.Deque;

import org.bitbucket.easymath.annotations.NumberType;
import org.bitbucket.easymath.processor.mathematical.operation.operand.Operand;

public abstract class AbstractOperation implements Operation {
    
    private int id;
    private String operator;
    private NumberType type;

    public AbstractOperation(int id, String operator, NumberType type) {
        super();
        this.id = id;
        this.operator = operator;
        this.type = type;
    }

    public abstract Deque<Operand> getOperands();
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    public String getOperator() {
        return operator;
    }

    public NumberType getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return String.format("r%s", id);
    }
}
