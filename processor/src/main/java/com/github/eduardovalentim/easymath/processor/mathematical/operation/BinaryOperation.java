package com.github.eduardovalentim.easymath.processor.mathematical.operation;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import com.github.eduardovalentim.easymath.processor.mathematical.operation.operand.Operand;

/**
 * A operation with two operands
 * 
 * @author eduardo.valentim
 */
public class BinaryOperation extends AbstractOperation {

	private Operand leftOperand;
	private Operand rightOperand;

	/**
	 * Public constructor
	 * 
	 * @param id
	 *            The id
	 * @param type
	 *            The type
	 * @param leftOperand
	 *            The left operand
	 * @param operator
	 *            The operator
	 * @param rightOperand
	 *            The right operand
	 * @param text
	 *            The text
	 */
	public BinaryOperation(String id, String type, Operand leftOperand, String operator, Operand rightOperand,
			String text) {
		super(id, operator, type, text);
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Deque<Operand> getOperands() {
		return new LinkedList<>(Arrays.asList(leftOperand, rightOperand));
	}

	/**
	 * Get
	 * 
	 * @return The left operand
	 */
	public Operand getLeftOperand() {
		return leftOperand;
	}

	/**
	 * Get
	 * 
	 * @return The right operand
	 */
	public Operand getRightOperand() {
		return rightOperand;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "BinaryOperation [left=" + leftOperand + ", operator=" + getOperator() + ", right=" + rightOperand + "]";
	}

}
