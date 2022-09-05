package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import com.github.eduardovalentim.easymath.annotations.Formula;

/**
 * Test class
 * 
 * @author eduardovalentim
 */
public interface Algebra {

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("a!")
	public double factorial(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("(a ^ 2) - (b ^ 2)")
	public double squaresDifference(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("(a - b) * (a + b)")
	public BigDecimal squaresDifferenceExpansion(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("a ^ 3 - b ^ 3")
	public double cubesDifference(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("(a - b) * (a ^ 2 + a * b + b ^ 2)")
	public BigDecimal cubesDifferenceExpansion(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("(a ^ 3) + (b ^ 3)")
	public double cubesSum(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("(a + b) * ((a ^ 2) - (a * b) + (b ^ 2))")
	public BigDecimal cubesSumExpansion(Number... args);
	
	/**
	 * 
	 * @param args
	 * @return
	 */
	@Formula("(b ^ 2) - (4 * a * c)")
	public double quadraticEquation(Number... args);
}
