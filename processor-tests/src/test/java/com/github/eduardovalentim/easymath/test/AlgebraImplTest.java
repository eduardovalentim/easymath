package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlgebraImplTest {

	private Algebra algebra;
	
	@BeforeEach
	void before() {
		algebra = new AlgebraImpl();
	}

	@Test
	void testSquaresDifferenceNumberArray() {
		double expected = algebra.squaresDifference(7.3d, 3.7d);
		BigDecimal actual = algebra.squaresDifferenceExpansion(new BigDecimal("7.3"), new BigDecimal("3.7"));

		Assertions.assertEquals(expected, actual.doubleValue(), 0.001);
	}

	@Test
	void testCubesDifferenceNumberArray() {
		double expected = algebra.cubesDifference(7.3d, 3.7d);
		BigDecimal actual = algebra.cubesDifferenceExpansion(new BigDecimal("7.3"), new BigDecimal("3.7"));

		Assertions.assertEquals(expected, actual.doubleValue(), 0.001);
	}

	@Test
	void testCubesSumDoubleDouble() {
		double expected = algebra.cubesSum(7.3d, 3.7d);
		BigDecimal actual = algebra.cubesSumExpansion(new BigDecimal("7.3"), new BigDecimal("3.7"));

		Assertions.assertEquals(expected, actual.doubleValue(), 0.001);
	}

	@Test
	void testquadraticEquation() {
		double actual = algebra.quadraticEquation(10D, 20D, 30D);

		Assertions.assertEquals(-2300.0, actual, 0.001);
	}
}
