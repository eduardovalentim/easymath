package br.com.easymath.functions;

import static br.com.easymath.Numbers.toDouble;

import java.math.MathContext;

import br.com.easymath.Function;

/**
 * 
 * @author eduardovalentim
 */
public class PowerFunction implements Function<Number> {

	public static final PowerFunction INSTANCE = new PowerFunction();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String name() {
		return "pow";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Number perform(MathContext mc, Number... inputs) {
		/*
		 * Method protection block
		 */
		if (inputs == null)
			throw new IllegalArgumentException("Argument 'inputs' cannot be null.");
		if (inputs.length != 2)
			throw new IllegalArgumentException(
					"Length mismatch for argument 'inputs'. Expected '2' actual '" + inputs.length + "'");
		/*
		 * Typecast inputs
		 */
		double base = toDouble(inputs[0], 0);
		double exponent = toDouble(inputs[1], 1);
		/*
		 * Result
		 */
		return Math.pow(base, exponent);
	}

}
