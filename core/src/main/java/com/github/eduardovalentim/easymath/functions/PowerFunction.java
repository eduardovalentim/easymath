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

	/**
	 * Constructor
	 */
    public PowerFunction() {
    	super();
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
		validate(2, inputs);

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
