package br.com.easymath.processor.mathematical.operation;

/**
 * @author eduardo.valentim
 */
public interface Operation {

    /**
     * 
     * @return
     */
    public String getId();
    
    /**
     * 
     * @return
     */
    public String getOperator();
    
    /**
     * 
     * @return
     */
    public String getType();
    
    /**
     * 
     * @return
     */
    public String getText();

}
