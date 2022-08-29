package com.github.eduardovalentim.easymath.processor.mathematical.operation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import com.github.eduardovalentim.easymath.processor.mathematical.operation.operand.Operand;

/**
 * A unary operation
 * 
 * @author eduardo.valentim
 */
public class UnaryOperation extends AbstractOperation {

	private Operand operand;

	/**
	 * Public constructor
	 * 
	 * @param id
	 *            The id
	 * @param type
	 *            The type
	 * @param operand
	 *            The operand
	 * @param operator
	 *            The operator
	 * @param text
	 *            The text
	 */
	public UnaryOperation(String id, String type, Operand operand, String operator, String text) {
		super(id, operator, type, text);
		this.operand = operand;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Deque<Operand> getOperands() {
		return new LinkedList<>(Arrays.asList(operand));
	}

	/**
	 * The operand
	 * 
	 * @return The operand
	 */
	public Operand getOperand() {
		return operand;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "UnaryOperation [operator=" + getOperator() + ", operand=" + operand + "]";
	}
}
