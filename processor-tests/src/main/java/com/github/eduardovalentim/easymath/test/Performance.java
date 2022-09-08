package com.github.eduardovalentim.easymath.test;

import com.github.eduardovalentim.easymath.annotations.EasyMath;
import com.github.eduardovalentim.easymath.annotations.Formula;

@EasyMath
public interface Performance {

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
	public double cubesDifferenceExpansion(Number... args);
}
