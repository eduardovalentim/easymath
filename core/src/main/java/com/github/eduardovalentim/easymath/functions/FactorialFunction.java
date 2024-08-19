package com.github.eduardovalentim.easymath.functions;

import java.math.BigInteger;
import java.math.MathContext;

import com.github.eduardovalentim.easymath.Function;

/**
 * Factorial function
 * 
 * @author eduardovalentim
 */
public class FactorialFunction implements Function<BigInteger> {

	/**
	 * Constructor
	 */
    public FactorialFunction() {
    	super();
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String name() {
		return "fat";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigInteger perform(MathContext mc, Number... inputs) {
		/*
		 * Method protection block
		 */
		validate(1, inputs);
		/*
		 * Default result
		 */
		BigInteger fat = BigInteger.ONE;
		/*
		 * Calculate
		 */
		BigInteger signal = BigInteger.ONE;
		
		BigInteger val = BigInteger.valueOf(inputs[0].longValue());
		if (val.compareTo(BigInteger.ZERO) < 0) {
			signal = signal.negate();
			val = val.abs();
		}
		
		if (val.compareTo(BigInteger.ONE) != 0) {
			do {
				fat = fat.multiply(val);
				val = val.subtract(BigInteger.ONE);
			} while (val.compareTo(BigInteger.ZERO) > 0);
		}
		/*
		 * Result
		 */
		return fat.multiply(signal);
	}

}
