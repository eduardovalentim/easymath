package com.github.eduardovalentim.easymath.functions;

import java.math.BigInteger;
import java.math.MathContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactorialFunctionTest {

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialMinusSix() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, -6);
		BigInteger expected = BigInteger.valueOf(-720L);
		
		Assertions.assertTrue(expected.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialMinusFive() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, -5);
		BigInteger expected = BigInteger.valueOf(-120L);
		
		Assertions.assertTrue(expected.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialMinusFour() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, -4);
		BigInteger expected = BigInteger.valueOf(-24L);
		
		Assertions.assertTrue(expected.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialMinusThree() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, -3);
		BigInteger expected = BigInteger.valueOf(-6L);

		Assertions.assertTrue(expected.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialMinusTwo() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, -2);
		BigInteger expected = BigInteger.valueOf(-2L);

		Assertions.assertTrue(expected.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialMinusOne() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, -1);

		Assertions.assertTrue(BigInteger.ONE.negate().compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialZero() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, 0);

		Assertions.assertTrue(BigInteger.ZERO.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialOne() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, 1);

		Assertions.assertTrue(BigInteger.ONE.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialTwo() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, 2);
		BigInteger expected = BigInteger.valueOf(2L);
		
		Assertions.assertTrue(expected.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialThree() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, 3);
		BigInteger expected = BigInteger.valueOf(6L);
		
		Assertions.assertTrue(expected.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialFour() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, 4);
		BigInteger expected = BigInteger.valueOf(24L);
		
		Assertions.assertTrue(expected.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialFive() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, 5);
		BigInteger expected = BigInteger.valueOf(120L);
		
		Assertions.assertTrue(expected.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialSix() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, 6);
		BigInteger expected = BigInteger.valueOf(720L);
		
		Assertions.assertTrue(expected.compareTo(actual) == 0);
	}

	/**
	 * Factorial method test
	 */
	@Test
	public void testFactorialOneHundredTwentySeven() {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, 127);
		BigInteger expected = new BigInteger("3012660018457659544809977077527059692324164918673621799053346900596667207618480809067860692097713761984609779945772783965563851033300772326297773087851869982500270661791244122597621760000000000000000000000000000000");
		
		Assertions.assertTrue(expected.compareTo(actual) == 0);
	}
}
