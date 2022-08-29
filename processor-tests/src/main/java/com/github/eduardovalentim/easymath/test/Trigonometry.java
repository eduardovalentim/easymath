package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import com.github.eduardovalentim.easymath.annotations.Formula;

/**
 * Test class
 * @author eduardovalentim
 *
 */
public interface Trigonometry {

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("sin(alpha + beta)")
	public abstract BigDecimal sinAB(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("sin(alpha) * cos(beta) + sin(beta) * cos(alpha)")
	public abstract double sinABExpansion(Number... args);
}
