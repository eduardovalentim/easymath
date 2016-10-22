package br.com.easymath.processor.mathematical.operation.operand;

/**
 * @author eduardo.valentim
 */
public class InputOperand extends AbstractOperand {

    /**
     * 
     * @param type
     * @param value
     */
    public InputOperand(String type, String value) {
        super(value, type, value);
    }
}
