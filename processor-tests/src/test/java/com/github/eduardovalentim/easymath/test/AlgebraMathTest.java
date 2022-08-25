package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;
import java.text.MessageFormat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlgebraMathTest {

	@Test
	public void testSquaresDifferenceNumberArray() {
		AlgebraMath algebra = new AlgebraMath();

		double expected = algebra.squaresDifference(7.3d, 3.7d);
		BigDecimal actual = algebra.squaresDifferenceExpansion(new BigDecimal("7.3"), new BigDecimal("3.7"));

		Assertions.assertEquals(expected, actual.doubleValue(), 0.001);
	}

	@Test
	public void testCubesDifferenceNumberArray() {
		AlgebraMath algebra = new AlgebraMath();

		double expected = algebra.cubesDifference(7.3d, 3.7d);
		BigDecimal actual = algebra.cubesDifferenceExpansion(new BigDecimal("7.3"), new BigDecimal("3.7"));

		Assertions.assertEquals(expected, actual.doubleValue(), 0.001);
	}

	@Test
	public void testCubesSumDoubleDouble() {
		AlgebraMath algebra = new AlgebraMath();

		double expected = algebra.cubesSum(7.3d, 3.7d);
		BigDecimal actual = algebra.cubesSumExpansion(new BigDecimal("7.3"), new BigDecimal("3.7"));

		Assertions.assertEquals(expected, actual.doubleValue(), 0.001);
	}

	@Test
	public void testquadraticEquation() {
		AlgebraMath algebra = new AlgebraMath();

		double actual = algebra.quadraticEquation(10D, 20D, 30D);

		Assertions.assertEquals(-2300.0, actual, 0.001);
	}

	@Test
	public void testLoggerFormat() {
		String msg = MessageFormat.format("Entering with arguments ({0, number} ^ 2) - ({1, number} ^ 2)", 11.1D, 12.2F);
		System.out.println(msg);
	}
}
