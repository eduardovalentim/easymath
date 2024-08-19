package com.github.eduardovalentim.easymath.functions;

import java.math.MathContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PowerFunctionTest {

	private static final int MAX_EXCEL_VISIBLE_FRACTIONS = 14;
	
	private PowerFunction power;
	
	@BeforeEach
	void init() {
		power = new PowerFunction();
	}
	
	@Test
	void testSquareRootFunctionName() {
		Assertions.assertEquals("pow", power.name());
	}
	
	/**
	 * Power method test
	 */
	@ParameterizedTest
	@CsvSource({
		"-5,-120,1.3292279957849158E-84",
		"-4,-24,3.552713678800501E-15",
		"-3,-6,0.0013717421124828533",
		"-2,-2,0.25",
		"-1,-1,-1",
		"0,0,1",
		"1,1,1",
		"2,2,4",
		"3,6,729.0",
		"4,24,2.81474976710656E14",
		"5,120,7.52316384526264E83"
	})
	void testPower(Double base, Double exponent, Double expected) {
		Double actual = power.perform(MathContext.DECIMAL32, base, exponent);
		Assertions.assertEquals(truncateTo(expected, MAX_EXCEL_VISIBLE_FRACTIONS), truncateTo(actual, MAX_EXCEL_VISIBLE_FRACTIONS));
	}
	
	@Test()
	@SuppressWarnings({"all"})
	void testSquareNullInputs() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			power.perform(MathContext.DECIMAL32, null);
		});
	}

	@Test()
	void testSquareNoInputs() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			power.perform(MathContext.DECIMAL32);
		});
	}

	@Test()
	void testSquareLengthMismatchInputs() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			power.perform(MathContext.DECIMAL32, 1, 2, 3);
		});
	}
	
	static double truncateTo( double unroundedNumber, int decimalPlaces ){
	    int truncatedNumberInt = (int)( unroundedNumber * Math.pow( 10, decimalPlaces ) );
	    double truncatedNumber = (double)( truncatedNumberInt / Math.pow( 10, decimalPlaces ) );
	    return truncatedNumber;
	}
}
