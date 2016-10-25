package br.com.easymath.functions;

import java.math.MathContext;

import br.com.easymath.Function;
import br.com.easymath.Numbers;

/**
 * @author eduardovalentim
 */
public class SquareRootFunction implements Function<Double> {

	public static final SquareRootFunction INSTANCE = new SquareRootFunction();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String name() {
		return "sqrt";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double perform(MathContext mc, Number... inputs) {
		/*
		 * Method protection block
		 */
		if (inputs == null)
			throw new IllegalArgumentException("Argument 'inputs' cannot be null.");
		if (inputs.length != 1)
			throw new IllegalArgumentException(
					"Length mismatch for argument 'inputs'. Expected '1' actual '" + inputs.length + "'");
		/*
		 * Typecast inputs
		 */
		double x = Numbers.toDouble(inputs[0], 0);
		/*
		 * Result
		 */
		return Math.sqrt(x);
	}

}
