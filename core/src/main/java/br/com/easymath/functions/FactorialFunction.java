package br.com.easymath.functions;

import java.math.BigInteger;
import java.math.MathContext;

import br.com.easymath.Function;

/**
 * 
 * @author eduardovalentim
 */
public class FactorialFunction implements Function<BigInteger> {

	public static final FactorialFunction INSTANCE = new FactorialFunction();
	
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
