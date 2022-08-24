package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BasicMathTest {

	@Test
	public void testAddNumberArray() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.add(1, BigDecimal.TEN);
		
		Assertions.assertTrue(Double.valueOf(11).compareTo(actual) == 0);
	}

	@Test
	public void testAddDoubleDouble() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.add(1d, 10d);
		
		Assertions.assertTrue(Double.valueOf(11).compareTo(actual) == 0);
	}

	@Test
	public void testSubtractNumberArray() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.subtract(1, BigDecimal.ONE);
		
		Assertions.assertTrue(Double.valueOf(0).compareTo(actual) == 0);
	}

	@Test
	public void testSubtractDoubleDouble() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.subtract(1d, 1d);
		
		Assertions.assertTrue(Double.valueOf(0).compareTo(actual) == 0);
	}

	@Test
	public void testDivideNumberArray() {
		BasicMath basic = new BasicMath();
		
		BigDecimal actual = basic.divide(10d, 3l);
		
		Assertions.assertTrue(new BigDecimal("3.333333").compareTo(actual) == 0);
	}

	@Test
	public void testDivideBigDecimalBigDecimal() {
		BasicMath basic = new BasicMath();
		
		BigDecimal actual = basic.divide(BigDecimal.TEN, new BigDecimal("3"));
		
		Assertions.assertTrue(new BigDecimal("3.333333").compareTo(actual) == 0);
	}

	@Test
	public void testMutiplyNumberArray() {
		BasicMath basic = new BasicMath();
		
		BigDecimal actual = basic.mutiply(5l, 2.5d);
		
		Assertions.assertTrue(new BigDecimal("12.5").compareTo(actual) == 0);
	}

	@Test
	public void testMutiplyBigDecimalBigDecimal() {
		BasicMath basic = new BasicMath();
		
		BigDecimal actual = basic.mutiply(new BigDecimal("5"), new BigDecimal("2.5"));
		
		Assertions.assertTrue(new BigDecimal("12.5").compareTo(actual) == 0);
	}

}
