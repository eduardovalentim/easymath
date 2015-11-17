package org.bitbucket.easymath.processor.mathematical.operation;

public abstract class AbstractOperation implements Operation {

    private int id;
    private String operator;

    public AbstractOperation(int id, String operator) {
        super();
        this.id = id;
        this.operator = operator;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    public String getOperator() {
        return operator;
    }
    
    public static String declareId(int id) {
        return String.format("r%d", id);
    }    
}
