package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import com.github.eduardovalentim.easymath.annotations.Formula;

/**
 * Test class
 * 
 * @author eduardovalentim
 */
public abstract class Algebra {

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("(a ^ 2) - (b ^ 2)")
	public abstract double squaresDifference(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("(a - b) * (a + b)")
	public abstract BigDecimal squaresDifferenceExpansion(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("a ^ 3 - b ^ 3")
	public abstract double cubesDifference(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("(a - b) * (a ^ 2 + a * b + b ^ 2)")
	public abstract BigDecimal cubesDifferenceExpansion(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("(a ^ 3) + (b ^ 3)")
	public abstract double cubesSum(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("(a + b) * ((a ^ 2) - (a * b) + (b ^ 2))")
	public abstract BigDecimal cubesSumExpansion(Number... args);
}
