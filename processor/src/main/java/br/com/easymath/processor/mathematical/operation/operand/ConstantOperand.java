package br.com.easymath.processor.mathematical.operation.operand;

/**
 * @author eduardo.valentim
 */
public class ConstantOperand extends AbstractOperand {

    /**
     * 
     * @param type
     * @param value
     */
    public ConstantOperand(String type, String value) {
        super(formatId(type, value), type, value);
    }

    private static String formatId(String type, String value) {
        String id;
        
        if (Double.class.getCanonicalName().equals(type)) {
            id = String.format("D%s", value);
        } else {
            id = String.format("BD%s", value);
        }
        
        return id.replace(".", "_");
    }
}
