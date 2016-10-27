package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import com.github.eduardovalentim.easymath.annotations.Formula;

/**
 * Test class
 * @author eduardo.valentim
 */
public abstract class Basic {

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("a + b")
	public abstract double add(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("a - b")
	public abstract Double subtract(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("a / b")
	public abstract BigDecimal divide(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula("a * b")
	public abstract BigDecimal mutiply(Number... args);

}
