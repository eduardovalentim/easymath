package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConstantsMathTest {

	private Constants constants;
	
	@BeforeEach
	void before() {
		constants = new ConstantsImpl();
	}

	@Test
	void testAdding() {
		BigDecimal actual = constants.adding();
		BigDecimal expected = constants.addingExpansion();

		Assertions.assertEquals(expected, actual);
	}

	@Test
	void testSubtracting() {
		BigDecimal actual = constants.subtracting();
		BigDecimal expected = constants.subtractingExpansion();

		Assertions.assertEquals(expected, actual);
	}
}
