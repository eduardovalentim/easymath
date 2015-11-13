package org.bitbucket.easymath.processor.mathematical;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.bitbucket.easymath.processor.mathematical.operation.FormulaOperation;

public class JavaCode {

	private String name;
	private String expression;
	private String type;
	
	public Set<String> constants;
	public Set<String> inputs;

	public List<FormulaOperation> operations;
	
	public JavaCode(String name, String expression, String type) {
		super();
		this.name = name;
		this.expression = expression;
		this.type = type;
		this.constants = new HashSet<>();
		this.inputs = new HashSet<>();
		this.operations = new LinkedList<>();
	}

	public String getName() {
		return name;
	}
	
	public String getExpression() {
		return expression;
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

	public boolean addOperation(FormulaOperation e) {
		return operations.add(e);
	}
	
	public List<FormulaOperation> getOperations() {
		return Collections.unmodifiableList(operations);
	}
}
