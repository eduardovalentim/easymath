package br.com.easymath.functions;

import static br.com.easymath.Numbers.toApfloat;
import static org.apfloat.ApfloatMath.sqrt;

import java.math.MathContext;

import org.apfloat.Apfloat;

import br.com.easymath.Function;

/**
 * @author eduardovalentim
 */
public class SquareRootFunction implements Function<Number> {

	public static final SquareRootFunction INSTANCE = new SquareRootFunction();
	
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
	public Number perform(MathContext mc, Number... inputs) {
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
		Apfloat x = toApfloat(inputs[0], 0);
		/*
		 * Result
		 */
		return sqrt(x);
	}

}
