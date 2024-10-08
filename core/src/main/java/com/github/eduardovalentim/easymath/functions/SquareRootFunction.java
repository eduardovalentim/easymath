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

	/**
	 * Constructor
	 */
    public SquareRootFunction() {
    	super();
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
		validate(1, inputs);

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
