package com.github.eduardovalentim.easymath.functions;

import java.math.MathContext;

import com.github.eduardovalentim.easymath.Function;
import com.github.eduardovalentim.easymath.Numbers;

/**
 * Square root function
 * 
 * @author eduardovalentim
 */
public class SquareRootFunction implements Function<Double> {

    private static class SquareRootFunctionHelper {
        private static final SquareRootFunction INSTANCE = new SquareRootFunction();
    }
	
	/**
	 * Public instance
	 */
	public static final SquareRootFunction getInstance() {
        return SquareRootFunctionHelper.INSTANCE;
	}
	
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
