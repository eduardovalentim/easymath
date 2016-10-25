package br.com.easymath;

import static java.text.MessageFormat.format;

import java.math.BigDecimal;

/**
 * 
 * @author Eduardo.Valentim
 *
 */
public class Numbers {

	private static final String EXCEPTION_MESSAGE_TEMPLATE = "Argument ''inputs[{0}]'' cannot be null.";

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
        if (number instanceof BigDecimal) {
            result = (BigDecimal)number;
        } else {
            result = new BigDecimal(number.toString()); 
        }
        /*
         * Return the result
         */
        return result;
    }

	public static Double toDouble(Number number, int inputIndex) {
		/*
		 * Method protection block
		 */
		if (number == null)
			throw new IllegalArgumentException(format(EXCEPTION_MESSAGE_TEMPLATE, inputIndex));

		return number.doubleValue();
	}
}
