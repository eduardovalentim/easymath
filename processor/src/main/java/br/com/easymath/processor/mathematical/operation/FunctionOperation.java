package br.com.easymath.processor.mathematical.operation;

import java.util.Deque;

import br.com.easymath.processor.mathematical.operation.operand.Operand;

/**
 * A function operation
 * 
 * @author eduardo.valentim
 */
public class FunctionOperation extends AbstractOperation {

	private String name;
	private Deque<Operand> operands;

	/**
	 * Public constructor
	 * 
	 * @param id
	 *            The id
	 * @param name
	 *            The name
	 * @param type
	 *            The type
	 * @param operands
	 *            The operands
	 * @param text
	 *            The text
	 */
	public FunctionOperation(String id, String name, String type, Deque<Operand> operands, String text) {
		super(id, null, type, text);
		this.name = name;
		this.operands = operands;
	}

	/**
	 * Get
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Deque<Operand> getOperands() {
		return operands;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "FunctionOperation [name=" + name + ", operands={" + operands + "}]";
	}

}
