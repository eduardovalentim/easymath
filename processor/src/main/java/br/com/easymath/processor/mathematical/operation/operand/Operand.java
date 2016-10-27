package br.com.easymath.processor.mathematical.operation.operand;

/**
 * The definition of a operand
 * 
 * @author eduardo.valentim
 */
public interface Operand {

	/**
	 * Get
	 * 
	 * @return The id
	 */
	public String getId();

	/**
	 * Get
	 * 
	 * @return The type
	 */
	public String getType();

	/**
	 * Get
	 * 
	 * @return The value
	 */
	public String getValue();

}
