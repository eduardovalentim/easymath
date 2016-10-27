package com.github.eduardovalentim.easymath.test.functions;

import static com.github.eduardovalentim.easymath.Numbers.toDouble;

import java.math.MathContext;

import com.github.eduardovalentim.easymath.Function;

public class SinFunction implements Function<Number> {

	public static final SinFunction INSTANCE = new SinFunction();
	
	@Override
	public String name() {
		return "sin";
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
		return Math.sin(a);
	}

}
