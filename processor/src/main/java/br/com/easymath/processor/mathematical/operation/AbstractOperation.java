package br.com.easymath.processor.mathematical.operation;

import java.util.Deque;

import br.com.easymath.processor.mathematical.operation.operand.Operand;

/**
 * @author eduardo.valentim
 */
public abstract class AbstractOperation implements Operation {
    
    private String id;
    private String operator;
    private String type;
    private String text;

    /**
     * 
     * @param id
     * @param operator
     * @param type
     * @param text
     */
    public AbstractOperation(String id, String operator, String type, String text) {
        super();
        this.id = id;
        this.operator = operator;
        this.type = type;
        this.text = text;
    }

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
