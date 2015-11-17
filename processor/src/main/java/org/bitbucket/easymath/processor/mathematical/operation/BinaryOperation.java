package org.bitbucket.easymath.processor.mathematical.operation;

public class BinaryOperation extends AbstractOperation {

    private String leftOperand;
    private String rightOperand;
    
    public BinaryOperation(int id, String leftOperand, String operator, String rightOperand) {
        super(id, operator);
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    /**
     * @return the leftOperand
     */
    public String getLeftOperand() {
        return leftOperand;
    }

    /**
     * @return the rightOperand
     */
    public String getRightOperand() {
        return rightOperand;
    }
}
