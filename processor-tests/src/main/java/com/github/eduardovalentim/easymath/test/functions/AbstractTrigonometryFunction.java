package com.github.eduardovalentim.easymath.test.functions;

import static com.github.eduardovalentim.easymath.Numbers.toDouble;

import java.math.MathContext;

import com.github.eduardovalentim.easymath.Function;

public abstract class AbstractTrigonometryFunction implements Function<Double> {

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
		if (inputs[0] == null)
			throw new IllegalArgumentException("Argument 'inputs[0]' cannot be null.");
		/*
		 * Inputs typecast
		 */
		return toDouble(inputs[0], 0);
	}

}
