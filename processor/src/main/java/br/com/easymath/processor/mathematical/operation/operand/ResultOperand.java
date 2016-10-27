package br.com.easymath.processor.mathematical.operation.operand;

/**
 * The result operand
 * 
 * @author eduardo.valentim
 */
public class ResultOperand extends AbstractOperand {

	/**
	 * Public constructor
	 * 
	 * @param type
	 *            The type
	 * @param value
	 *            The value
	 */
	public ResultOperand(String type, String value) {
		super(value, type, value);
	}

}
