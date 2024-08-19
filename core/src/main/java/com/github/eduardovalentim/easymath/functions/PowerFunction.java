package com.github.eduardovalentim.easymath.functions;

import static com.github.eduardovalentim.easymath.Numbers.toDouble;

import java.math.MathContext;

import com.github.eduardovalentim.easymath.Function;

/**
 * Power function
 * 
 * @author eduardovalentim
 */
public class PowerFunction implements Function<Double> {

    private static class PowerFunctionHelper {
        private static final PowerFunction INSTANCE = new PowerFunction();
    }
	
	/**
	 * Public instance
	 */
	public static PowerFunction getInstance() {
        return PowerFunctionHelper.INSTANCE;
	}
	
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
	public Double perform(MathContext mc, Number... inputs) {
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
