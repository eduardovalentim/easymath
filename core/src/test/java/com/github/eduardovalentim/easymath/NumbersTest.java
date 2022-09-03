package com.github.eduardovalentim.easymath;

import java.math.BigDecimal;
import java.math.MathContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NumbersTest {

	private static Double ONE = Double.valueOf(1.0d);
	
	@Test
	void testToBigDecimalNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Numbers.toBigDecimal(null, 0);
		});
	}

	@Test
	void testToBigDecimalWithBigDecimal() {
		BigDecimal actual = Numbers.toBigDecimal(BigDecimal.ONE, 0);
		Assertions.assertEquals(BigDecimal.ONE, actual);
	}

	@Test
	void testToBigDecimalWithDouble() {
		BigDecimal actual = Numbers.toBigDecimal(ONE, 0);
		Assertions.assertEquals(new BigDecimal(ONE.toString()), actual);
	}

	@Test
	void testToDoubleWithNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Numbers.toDouble(null, 0);
		});
	}

	@Test
	void testToDoubleWithDouble() {
		Double actual = Numbers.toDouble(ONE, 0);
		Assertions.assertEquals(ONE, actual);
	}

	@Test
	void testToDoubleWithInteger() {
		Double actual = Numbers.toDouble(1, 0);
		Assertions.assertEquals(ONE, actual);
	}

	@Test
	void testToDoubleWithBigDecimal() {
		Double actual = Numbers.toDouble(BigDecimal.ONE, 0);
		Assertions.assertEquals(ONE, actual);
	}

	@Test
	void testRoundUp() {
		Double actual = Numbers.round(0.12345678d, MathContext.DECIMAL32);
		Assertions.assertEquals(0.1234568d, actual);
	}

	@Test
	void testRoundDown() {
		Double actual = Numbers.round(0.12345673d, MathContext.DECIMAL32);
		Assertions.assertEquals(0.1234567d, actual);
	}

	@Test
	void testRoundPositiveInfinity() {
		Double actual = Numbers.round(Double.POSITIVE_INFINITY, MathContext.DECIMAL32);
		Assertions.assertEquals(Double.POSITIVE_INFINITY, actual);
	}

	@Test
	void testRoundNegativeInfinity() {
		Double actual = Numbers.round(Double.NEGATIVE_INFINITY, MathContext.DECIMAL32);
		Assertions.assertEquals(Double.NEGATIVE_INFINITY, actual);
	}

	@Test
	void testRoundWithMathContextNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Numbers.round(Double.NEGATIVE_INFINITY, null);
		});
	}

	@Test
	void testRoundWithZero() {
		Double actual = Numbers.round(0.0d, MathContext.DECIMAL32);
		Assertions.assertEquals(0.0d, actual);
	}

	@Test
	void testRoundWithNan() {
		MathContext mc = Mockito.mock(MathContext.class);
		Mockito.when(mc.getPrecision()).thenThrow(NumberFormatException.class);
		
		Double actual = Numbers.round(0.0d, mc);
		Assertions.assertEquals(Double.NaN, actual);
	}
}
