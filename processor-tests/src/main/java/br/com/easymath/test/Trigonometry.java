package br.com.easymath.test;

import java.math.BigDecimal;

import br.com.easymath.annotations.Formula;

/**
 * Test class
 * @author eduardovalentim
 *
 */
public abstract class Trigonometry {

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
