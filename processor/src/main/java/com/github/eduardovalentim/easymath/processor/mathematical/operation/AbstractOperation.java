package com.github.eduardovalentim.easymath.processor.mathematical.operation;

import java.util.Deque;

import com.github.eduardovalentim.easymath.processor.mathematical.operation.operand.Operand;

/**
 * A abstract operation definition to be specialized
 *  
 * @author eduardo.valentim
 */
public abstract class AbstractOperation implements Operation {
    
    private String id;
    private String operator;
    private String type;
    private String text;

    /**
     * Public constructor
     * 
     * @param id The id
     * @param operator The operator
     * @param type The type
     * @param text The text
     */
    protected AbstractOperation(String id, String operator, String type, String text) {
        super();
        this.id = id;
        this.operator = operator;
        this.type = type;
        this.text = text;
    }

    /**
     * The list of operands for this operation
     * @return
     */
    public abstract Deque<Operand> getOperands();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getOperator() {
        return operator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        return text;
    }
}
