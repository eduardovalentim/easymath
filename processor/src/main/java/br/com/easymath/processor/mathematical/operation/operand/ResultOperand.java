package br.com.easymath.processor.mathematical.operation.operand;

/**
 * @author eduardo.valentim
 */
public class ResultOperand extends AbstractOperand {

    /**
     * 
     * @param type
     * @param value
     */
    public ResultOperand(String type, String value) {
        super(value, type, value);
    }

}
