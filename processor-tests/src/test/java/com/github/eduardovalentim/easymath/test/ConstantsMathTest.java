package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConstantsMathTest {

	@Test
	public void testAdding() {
		ConstantsMath constants = new ConstantsMath();
		
		BigDecimal actual = constants.adding();
		BigDecimal expected = constants.addingExpansion();
		
		Assertions.assertTrue(actual.compareTo(expected) == 0);
	}

	@Test
	public void testSubtracting() {
		ConstantsMath constants = new ConstantsMath();
		
		BigDecimal actual = constants.subtracting();
		BigDecimal expected = constants.subtractingExpansion();
		
		Assertions.assertTrue(actual.compareTo(expected) == 0);
	}
}
