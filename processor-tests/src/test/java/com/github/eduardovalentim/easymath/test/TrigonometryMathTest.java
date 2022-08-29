package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.eduardovalentim.easymath.FunctionCatalog;
import com.github.eduardovalentim.easymath.test.functions.CosFunction;
import com.github.eduardovalentim.easymath.test.functions.SinFunction;

class TrigonometryMathTest {

	private Trigonometry trigonometry;

	@BeforeEach
	void before() {
		FunctionCatalog catalog = FunctionCatalog.valueOf(SinFunction.INSTANCE, CosFunction.INSTANCE);
		trigonometry = new TrigonometryImpl(catalog);
	}
	
	@Test
	void testSinABNumberArray() {
		BigDecimal actual = trigonometry.sinAB(45, 70);
		double expected = trigonometry.sinABExpansion(45d, 70d);
		
		Assertions.assertEquals(expected, actual.doubleValue(), 0.001);
	}

}
