package br.com.easymath.processor.mathematical.operation.operand;

/**
 * @author eduardo.valentim
 */
public interface Operand {

    /**
     * 
     * @return
     */
    public String getId();
    
    /**
     * 
     * @return
     */
    public String getType();
    
    /**
     * 
     * @return
     */
    public String getValue();
    
}
