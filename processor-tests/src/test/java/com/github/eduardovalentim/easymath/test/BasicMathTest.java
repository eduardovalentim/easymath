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

	@Test
	public void testMutiplyPrecision1() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.mutiplyPrecision1(0.1d, 0.2d);
		
		Assertions.assertTrue(Double.valueOf(0.0).compareTo(actual) == 0);
	}

	@Test
	public void testMutiplyPrecision2() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.mutiplyPrecision2(0.11d, 0.22d);
		
		Assertions.assertTrue(Double.valueOf(0.02).compareTo(actual) == 0);
	}

	@Test
	public void testMutiplyPrecision3() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.mutiplyPrecision3(0.111d, 0.222d);
		
		Assertions.assertTrue(Double.valueOf(0.025).compareTo(actual) == 0);
	}

	@Test
	public void testMutiplyPrecision4() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.mutiplyPrecision4(0.1111d, 0.2222d);
		
		Assertions.assertTrue(Double.valueOf(0.0247).compareTo(actual) == 0);
	}

	@Test
	public void testMutiplyPrecision5() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.mutiplyPrecision5(0.11111d, 0.22222d);
		
		Assertions.assertTrue(Double.valueOf(0.02469).compareTo(actual) == 0);
	}

	@Test
	public void testMutiplyPrecision6() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.mutiplyPrecision6(0.111111d, 0.222222d);
		
		Assertions.assertTrue(Double.valueOf(0.024691).compareTo(actual) == 0);
	}

	@Test
	public void testMutiplyPrecision7() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.mutiplyPrecision7(0.1111111d, 0.2222222d);
		
		Assertions.assertTrue(Double.valueOf(0.0246914).compareTo(actual) == 0);
	}

	@Test
	public void testMutiplyPrecision8() {
		BasicMath basic = new BasicMath();
		
		double actual = basic.mutiplyPrecision8(0.11111111d, 0.22222222d);
		
		Assertions.assertTrue(Double.valueOf(0.02469136).compareTo(actual) == 0);
	}
}
