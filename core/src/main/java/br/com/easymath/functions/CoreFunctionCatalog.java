package br.com.easymath.functions;

import br.com.easymath.FunctionCatalog;

/**
 * @author eduardovalentim
 */
public class CoreFunctionCatalog extends FunctionCatalog {

	/**
	 * Default public constructor
	 */
	public CoreFunctionCatalog() {
		super("core");
		addFunction(PowerFunction.INSTANCE);
		addFunction(FatorialFunction.INSTANCE);
		addFunction(SquareRootFunction.INSTANCE);
	}

}
