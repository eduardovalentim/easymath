package com.github.eduardovalentim.easymath.functions;

import java.math.MathContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SquareRootFunctionTest {

	private SquareRootFunction square;

	@BeforeEach
	void init() {
		square = new SquareRootFunction();
	}

	@Test
	void testSquareRootFunctionName() {
		Assertions.assertEquals("sqrt", square.name());
	}

	/**
	 * SquareRoot method test
	 */
	@ParameterizedTest
	@CsvSource({ "0,0", "1,1", "2,1.4142135623730951", "3,1.7320508075688772", "4,2.0", "5,2.23606797749979" })
	void testSquareRoot(Double value, Double expected) {
		Double actual = square.perform(MathContext.DECIMAL32, value);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void testSquareNan() {
		Double actual = square.perform(MathContext.DECIMAL32, -1);
		Assertions.assertEquals(Double.NaN, actual);
	}

	@Test()
	@SuppressWarnings({"all"})
	void testSquareNullInputs() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			square.perform(MathContext.DECIMAL32, null);
		});
	}

	@Test()
	void testSquareNoInputs() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			square.perform(MathContext.DECIMAL32);
		});
	}

	@Test()
	void testSquareLengthMismatchInputs() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			square.perform(MathContext.DECIMAL32, 1, 2, 3);
		});
	}
}
