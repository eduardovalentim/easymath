package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class BasicMathTest {

	@Test
	public void testAddNumberArray() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.add(1, BigDecimal.TEN);
		
		Assert.assertTrue(Double.valueOf(11).compareTo(actual) == 0);
	}

	@Test
	public void testAddDoubleDouble() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.add(1d, 10d);
		
		Assert.assertTrue(Double.valueOf(11).compareTo(actual) == 0);
	}

	@Test
	public void testSubtractNumberArray() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.subtract(1, BigDecimal.ONE);
		
		Assert.assertTrue(Double.valueOf(0).compareTo(actual) == 0);
	}

	@Test
	public void testSubtractDoubleDouble() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.subtract(1d, 1d);
		
		Assert.assertTrue(Double.valueOf(0).compareTo(actual) == 0);
	}

	@Test
	public void testDivideNumberArray() {
		BasicMath basic = new BasicMath();
		
		BigDecimal actual = basic.divide(10d, 3l);
		
		Assert.assertTrue(new BigDecimal("3.333333").compareTo(actual) == 0);
	}

	@Test
	public void testDivideBigDecimalBigDecimal() {
		BasicMath basic = new BasicMath();
		
		BigDecimal actual = basic.divide(BigDecimal.TEN, new BigDecimal("3"));
		
		Assert.assertTrue(new BigDecimal("3.333333").compareTo(actual) == 0);
	}

	@Test
	public void testMutiplyNumberArray() {
		BasicMath basic = new BasicMath();
		
		BigDecimal actual = basic.mutiply(5l, 2.5d);
		
		Assert.assertTrue(new BigDecimal("12.5").compareTo(actual) == 0);
	}

	@Test
	public void testMutiplyBigDecimalBigDecimal() {
		BasicMath basic = new BasicMath();
		
		BigDecimal actual = basic.mutiply(new BigDecimal("5"), new BigDecimal("2.5"));
		
		Assert.assertTrue(new BigDecimal("12.5").compareTo(actual) == 0);
	}

}
