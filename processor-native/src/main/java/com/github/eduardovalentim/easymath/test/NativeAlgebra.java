package com.github.eduardovalentim.easymath.test;


import com.github.eduardovalentim.easymath.annotations.EasyMath;
import com.github.eduardovalentim.easymath.annotations.Formula;
import com.github.eduardovalentim.easymath.annotations.GenerationMode;

/**
 * Test class
 * 
 * @author eduardovalentim
 */
@EasyMath(generationMode = GenerationMode.NATIVE)
public interface NativeAlgebra extends com.sun.jna.Library {

	/**
	 * A test method
	 * 
	 * @param a An input for this method
	 * @param b An input for this method
	 * 
	 * @return the result
	 */
	@Formula("a ^ 3 - b ^ 3")
	public double cubesDifference(double a, double b);

	/**
	 * A test method
	 * 
	 * @param a An input for this method
	 * @param b An input for this method
	 * 
	 * @return the result
	 */
	@Formula("(a - b) * (a ^ 2 + a * b + b ^ 2)")
	public double cubesDifferenceExpansion(double a, double b);
}
