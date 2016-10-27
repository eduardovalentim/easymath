package com.github.eduardovalentim.easymath.processor.mathematical.operation;

/**
 * A operation
 * 
 * @author eduardo.valentim
 */
public interface Operation {

	/**
	 * Get
	 * 
	 * @return The id
	 */
	public String getId();

	/**
	 * Get
	 * 
	 * @return The operator
	 */
	public String getOperator();

	/**
	 * Get
	 * 
	 * @return The type
	 */
	public String getType();

	/**
	 * Get
	 * 
	 * @return The text
	 */
	public String getText();

}
