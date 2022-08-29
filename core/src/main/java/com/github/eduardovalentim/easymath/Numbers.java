package com.github.eduardovalentim.easymath;

import static java.text.MessageFormat.format;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Utilities to handle numbers
 * 
 * @author Eduardo.Valentim
 */
public class Numbers {

	private static final String EXCEPTION_MESSAGE_TEMPLATE = "Argument ''inputs[{0}]'' cannot be null.";

    /** Positive zero. */
    private static final double POSITIVE_ZERO = 0d;

    /**
     * Private constructor to hide the implicit public one.
     */
    private Numbers() {
    	super();
    }
    
	/**
	 * Convert any number to a BigDecimal
	 * 
	 * @param number The number to be converted
	 * @param inputIndex An array index to log if the <code>number</code> is invalid
	 * @return The number converted to a BigDecimal
	 */
	public static BigDecimal toBigDecimal(Number number, int inputIndex) {
        /*
         * Method protection block
         */
        if (number == null)
            throw new IllegalArgumentException(format(EXCEPTION_MESSAGE_TEMPLATE, inputIndex));
        /*
         * Method result
         */
        BigDecimal result;
        /*
         * Check if the conversion is needed
         */
        if (number instanceof BigDecimal bigDecimal) {
            result = bigDecimal;
        } else {
            result = new BigDecimal(number.toString()); 
        }
        /*
         * Return the result
         */
        return result;
    }

	/**
	 * Convert any number to a Double
	 * 
	 * @param number The number to be converted
	 * @param inputIndex An array index to log if the <code>number</code> is invalid
	 * @return The number converted to a Double
	 */
	public static Double toDouble(Number number, int inputIndex) {
		/*
		 * Method protection block
		 */
		if (number == null)
			throw new IllegalArgumentException(format(EXCEPTION_MESSAGE_TEMPLATE, inputIndex));

		return number.doubleValue();
	}
	
	/**
	 * Code extracted from Apache Commons Math 3, Precision class, round method
	 * 
	 * @param x
	 * @param mc
	 * @return
	 */
    public static double round(double x, MathContext mc) {    	
		/*
		 * Method protection block
		 */
		if (mc == null)
			throw new IllegalArgumentException("'mc' argument cannot be null.");

    	double rounded = 0.0d;
		try {
            rounded = new BigDecimal(Double.toString(x), mc)
            		.setScale(mc.getPrecision(), mc.getRoundingMode())
            		.doubleValue();
            
            // MATH-1089: negative values rounded to zero should result in negative zero
            if (rounded == POSITIVE_ZERO ) {
            	rounded = POSITIVE_ZERO * x;
            }
        } catch (NumberFormatException ex) {
            if (Double.isInfinite(x)) {
            	rounded = x;
            } else {
            	rounded = Double.NaN;
            }
        }
		
		return rounded;
    }
}
