package br.com.easymath.functions;

import static org.apfloat.ApintMath.factorial;

import java.math.MathContext;

import br.com.easymath.Function;

/**
 * 
 * @author eduardovalentim
 */
public class FatorialFunction implements Function<Number> {

	public static final FatorialFunction INSTANCE = new FatorialFunction();
	
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
		 * Result
		 */
		return factorial(inputs[0].longValue());
	}

}
