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

	private static FactorialFunction instance;
	
	/**
	 * Public instance to be used
	 */
	public static FactorialFunction getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (FactorialFunction.class) {
                if (instance == null) { // Second check (with locking)
                    instance = new FactorialFunction();
                }
            }
        }
        return instance;
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
		if (inputs == null)
			throw new IllegalArgumentException("Argument 'inputs' cannot be null.");
		if (inputs.length != 1)
			throw new IllegalArgumentException(
					"Length mismatch for argument 'inputs'. Expected '1' actual '" + inputs.length + "'");
		if (inputs[0] == null)
			throw new IllegalArgumentException("Argument 'inputs[0]' cannot be null.");
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
