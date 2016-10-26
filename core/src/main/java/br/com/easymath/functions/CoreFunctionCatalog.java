package br.com.easymath.functions;

import br.com.easymath.FunctionCatalog;

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
		addFunction(PowerFunction.INSTANCE);
		addFunction(FactorialFunction.INSTANCE);
		addFunction(SquareRootFunction.INSTANCE);
	}

}
