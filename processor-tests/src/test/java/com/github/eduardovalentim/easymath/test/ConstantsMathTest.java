package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstantsMathTest {

	@Test
	void testAdding() {
		ConstantsMath constants = new ConstantsMath();

		BigDecimal actual = constants.adding();
		BigDecimal expected = constants.addingExpansion();

		Assertions.assertEquals(expected, actual);
	}

	@Test
	void testSubtracting() {
		ConstantsMath constants = new ConstantsMath();

		BigDecimal actual = constants.subtracting();
		BigDecimal expected = constants.subtractingExpansion();

		Assertions.assertEquals(expected, actual);
	}
}
