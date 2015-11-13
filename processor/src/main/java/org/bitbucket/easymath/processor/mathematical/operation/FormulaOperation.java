package org.bitbucket.easymath.processor.mathematical.operation;

public class FormulaOperation {

	private String left;
	private String right;
	private String operation;
	
	public FormulaOperation(String left, String right, String operation) {
		super();
		this.left = left;
		this.operation = operation;
		this.right = right;
	}

	public String getLeft() {
		return left;
	}

	public String getRight() {
		return right;
	}

	public String getOperation() {
		return operation;
	}

	@Override
	public String toString() {
		return left + " " + operation + " " + right;
	}
}
