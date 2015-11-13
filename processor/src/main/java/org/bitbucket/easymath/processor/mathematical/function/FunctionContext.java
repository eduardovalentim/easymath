package org.bitbucket.easymath.processor.mathematical.function;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class FunctionContext {

	private String name;
	private String formula;
	private String type;
	
	public Set<String> constants;
	public Set<String> inputs;

	public Stack<String> terms;
	
	public FunctionContext(String name, String expression, String type) {
		super();
		this.name = name;
		this.formula = expression;
		this.type = type;
		this.constants = new HashSet<>();
		this.inputs = new HashSet<>();
		this.terms = new Stack<>();
	}

	public String getName() {
		return name;
	}
	
	public String getFormula() {
		return formula;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean addConstant(String constant) {
		return constants.add(constant);
	}
	
	public Set<String> getConstants() {
		return Collections.unmodifiableSet(constants);
	}

	public boolean addInput(String e) {
		return inputs.add(e);
	}
	
	public Set<String> getInputs() {
		return Collections.unmodifiableSet(inputs);
	}

	public void pushTerm(String e) {
		terms.push(e);
	}
	
	@SuppressWarnings("unchecked")
    public Stack<String> getTerms() {
		return (Stack<String>) terms.clone();
	}
}
