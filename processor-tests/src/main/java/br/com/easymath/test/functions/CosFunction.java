package br.com.easymath.test.functions;

import static br.com.easymath.Numbers.toDouble;

import java.math.MathContext;

import br.com.easymath.Function;

public class CosFunction implements Function<Number> {

	public static final CosFunction INSTANCE = new CosFunction();
	
	@Override
	public String name() {
		return "cos";
	}

	@Override
	public Number perform(MathContext mc, Number... inputs) {
		/*
		 * Method protection block
		 */
		if (inputs == null)
			throw new IllegalArgumentException("Argument 'inputs' cannot be null.");
		if (inputs.length != 1)
			throw new IllegalArgumentException(
					"Length mismatch for argument 'inputs'. Expected '1' actual '" + inputs.length + "'");
		if (inputs[0] == null)
			throw new IllegalArgumentException("Argument 'inputs[0]' cannot be null.");
		/*
		 * Inputs typecast
		 */
		double a = toDouble(inputs[0], 0);
		/*
		 * Result
		 */
		return Math.cos(a);
	}

}
