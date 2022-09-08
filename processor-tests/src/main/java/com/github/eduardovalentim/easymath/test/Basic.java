package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import com.github.eduardovalentim.easymath.annotations.EasyMath;
import com.github.eduardovalentim.easymath.annotations.Formula;

/**
 * Test class
 * @author eduardo.valentim
 */
@EasyMath
public interface Basic {

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
	
	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula( value = "a * b", precision = 1)
	public abstract Double mutiplyPrecision1(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula( value = "a * b", precision = 2)
	public abstract Double mutiplyPrecision2(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula( value = "a * b", precision = 3)
	public abstract Double mutiplyPrecision3(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula( value = "a * b", precision = 4)
	public abstract Double mutiplyPrecision4(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula( value = "a * b", precision = 5)
	public abstract Double mutiplyPrecision5(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula( value = "a * b", precision = 6)
	public abstract Double mutiplyPrecision6(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula( value = "a * b", precision = 7)
	public abstract Double mutiplyPrecision7(Number... args);

	/**
	 * Test
	 * @param args inputs
	 * @return the result
	 */
	@Formula( value = "a * b", precision = 8)
	public abstract Double mutiplyPrecision8(Number... args);
}
