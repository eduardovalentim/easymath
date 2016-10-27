package br.com.easymath.processor.mathematical.grammar;

import java.math.RoundingMode;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import br.com.easymath.annotations.Formula;
import br.com.easymath.processor.mathematical.operation.Operation;
import br.com.easymath.processor.mathematical.operation.operand.ConstantOperand;
import br.com.easymath.processor.mathematical.operation.operand.InputOperand;

/**
 * A model to represent a function
 * 
 * @author eduardo.valentim
 */
public class FunctionModel {

	private String name;
	private String type;
	private Formula formula;
	private Collection<InputOperand> inputs;
	private Deque<Operation> operations;
	private Collection<ConstantOperand> constants;

	/**
	 * Public default constructor
	 */
	public FunctionModel() {
		this.inputs = new LinkedHashSet<>();
		this.operations = new LinkedList<>();
		this.constants = new LinkedHashSet<>();
	}

	/**
	 * Get
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Get
	 * 
	 * @return The precision
	 */
	public int getPrecision() {
		return formula.precision();
	}

	/**
	 * Get
	 * 
	 * @return The rounding mode
	 */
	public RoundingMode getRoundingMode() {
		return formula.roundingMode();
	}

	/**
	 * Get
	 * 
	 * @return the constants
	 */
	public Collection<ConstantOperand> getConstants() {
		return Collections.unmodifiableCollection(constants);
	}

	/**
	 * Add a constant to the list
	 * 
	 * @param c
	 *            The constant
	 * @return <tt>true</tt> if the constants collection changed as a result of
	 *         the call
	 * 
	 * @see java.util.Set#add(java.lang.Object)
	 */
	public boolean addConstant(ConstantOperand c) {
		return constants.add(c);
	}

	/**
	 * Add a list of constants to this model
	 * 
	 * @param c
	 *            A collection of constants
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * 
	 * @see java.util.Set#addAll(java.util.Collection)
	 */
	public boolean addAllConstants(Collection<? extends ConstantOperand> c) {
		return constants.addAll(c);
	}

	/**
	 * Get
	 * 
	 * @return the operations
	 */
	public Collection<Operation> getOperations() {
		return Collections.unmodifiableCollection(operations);
	}

	/**
	 * Add a operation to this model
	 * 
	 * @param o
	 *            A operation to add
	 * @return {@code true} (as specified by {@link Collection#add})
	 * 
	 * @see java.util.Deque#add(java.lang.Object)
	 */
	public boolean addOperation(Operation o) {
		return operations.add(o);
	}

	/**
	 * A 'list' of operations to add
	 * 
	 * @param c
	 *            The 'list'
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * 
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	public boolean addAllOperations(Collection<? extends Operation> c) {
		return operations.addAll(c);
	}

	/**
	 * Get
	 * 
	 * @return the inputs
	 */
	public Collection<InputOperand> getInputs() {
		return Collections.unmodifiableCollection(inputs);
	}

	/**
	 * Add a input to this model
	 * 
	 * @param e
	 *            The input
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * 
	 * @see java.util.Set#add(java.lang.Object)
	 */
	public boolean addInput(InputOperand e) {
		return inputs.add(e);
	}

	/**
	 * A 'list' of inputs to add
	 * 
	 * @param c
	 *            The 'list'
	 * @return <tt>true</tt> if this collection changed as a result of the call
	 * 
	 * @see java.util.Set#addAll(java.util.Collection)
	 */
	public boolean addAllInputs(Collection<? extends InputOperand> c) {
		return inputs.addAll(c);
	}

	/**
	 * Get
	 * 
	 * @return the formula
	 */
	public Formula getFormula() {
		return formula;
	}

	/**
	 * Set
	 * 
	 * @param formula
	 *            the formula to set
	 */
	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	/**
	 * Get
	 * 
	 * @return The last operation
	 */
	public Operation getLastOperation() {
		return operations.getLast();
	}
}
