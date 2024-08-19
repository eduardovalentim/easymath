package com.github.eduardovalentim.easymath.functions;

import com.github.eduardovalentim.easymath.FunctionCatalog;

/**
 * Standard catalog
 * 
 * @author eduardovalentim
 */
public class CoreFunctionCatalog extends FunctionCatalog {

	/**
	 * Default public constructor
	 */
	public CoreFunctionCatalog() {
		super("core");
		addFunction(PowerFunction.getInstance());
		addFunction(FactorialFunction.getInstance());
		addFunction(SquareRootFunction.getInstance());
	}

}
