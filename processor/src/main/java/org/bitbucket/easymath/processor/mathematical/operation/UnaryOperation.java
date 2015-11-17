package org.bitbucket.easymath.processor.mathematical.operation;

public class UnaryOperation extends AbstractOperation {

    private String operand;
    
    public UnaryOperation(int id, String operand, String operator) {
        super(id, operator);
        this.operand = operand;
    }

    /**
     * @return the operand
     */
    public String getOperand() {
        return operand;
    }
}
