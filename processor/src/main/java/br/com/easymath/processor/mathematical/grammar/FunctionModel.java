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
     * 
     * @param formula
     * @param inputs
     * @param constants
     * @param operations
     */
    public FunctionModel() {
        this.inputs = new LinkedHashSet<>();
        this.operations = new LinkedList<>();
        this.constants = new LinkedHashSet<>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     */
    public int getPrecision() {
		return formula.precision();
	}

    /**
     * 
     * @return
     */
	public RoundingMode getRoundingMode() {
		return formula.roundingMode();
	}

	/**
     * @return the constants
     */
    public Collection<ConstantOperand> getConstants() {
        return Collections.unmodifiableCollection(constants);
    }

    /**
     * @param e
     * @return
     * @see java.util.Set#add(java.lang.Object)
     */
    public boolean addConstant(ConstantOperand e) {
        return constants.add(e);
    }

    /**
     * @param c
     * @return
     * @see java.util.Set#addAll(java.util.Collection)
     */
    public boolean addAllConstants(Collection<? extends ConstantOperand> c) {
        return constants.addAll(c);
    }

    /**
     * @return the operations
     */
    public Collection<Operation> getOperations() {
        return Collections.unmodifiableCollection(operations);
    }

    
    /**
     * @param e
     * @return
     * @see java.util.Deque#add(java.lang.Object)
     */
    public boolean addOperation(Operation e) {
        return operations.add(e);
    }

    /**
     * @param c
     * @return
     * @see java.util.Collection#addAll(java.util.Collection)
     */
    public boolean addAllOperations(Collection<? extends Operation> c) {
        return operations.addAll(c);
    }

    /**
     * @return the inputs
     */
    public Collection<InputOperand> getInputs() {
        return Collections.unmodifiableCollection(inputs);
    }

    /**
     * @param e
     * @return
     * @see java.util.Set#add(java.lang.Object)
     */
    public boolean addInput(InputOperand e) {
        return inputs.add(e);
    }

    /**
     * @param c
     * @return
     * @see java.util.Set#addAll(java.util.Collection)
     */
    public boolean addAllInputs(Collection<? extends InputOperand> c) {
        return inputs.addAll(c);
    }

    /**
     * @return the formula
     */
    public Formula getFormula() {
        return formula;
    }

    /**
     * @param formula the formula to set
     */
    public void setFormula(Formula formula) {
        this.formula = formula;
    }
    
    /**
     * 
     * @return
     */
    public Operation getLastOperation() {
    	return operations.getLast();
    }
}
