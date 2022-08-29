package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicMathTest {

	@Test
	void testAddNumberArray() {
		BasicMath basic = new BasicMath();

		double actual = basic.add(1, BigDecimal.TEN);

		Assertions.assertEquals(Double.valueOf(11), actual, 0.001);
	}

	@Test
	void testAddDoubleDouble() {
		BasicMath basic = new BasicMath();

		double actual = basic.add(1d, 10d);

		Assertions.assertEquals(Double.valueOf(11), actual, 0.001);
	}

	@Test
	void testSubtractNumberArray() {
		BasicMath basic = new BasicMath();

		double actual = basic.subtract(1, BigDecimal.ONE);

		Assertions.assertEquals(Double.valueOf(0), actual, 0.001);
	}

	@Test
	void testSubtractDoubleDouble() {
		BasicMath basic = new BasicMath();

		double actual = basic.subtract(1d, 1d);

		Assertions.assertEquals(Double.valueOf(0), actual, 0.001);
	}

	@Test
	void testDivideNumberArray() {
		BasicMath basic = new BasicMath();

		BigDecimal actual = basic.divide(10d, 3l);

		Assertions.assertEquals(new BigDecimal("3.333333"), actual);
	}

	@Test
	void testDivideBigDecimalBigDecimal() {
		BasicMath basic = new BasicMath();

		BigDecimal actual = basic.divide(BigDecimal.TEN, new BigDecimal("3"));

		Assertions.assertEquals(new BigDecimal("3.333333"), actual);
	}

	@Test
	void testMutiplyNumberArray() {
		BasicMath basic = new BasicMath();

		BigDecimal actual = basic.mutiply(5l, 2.5d);

		Assertions.assertEquals(new BigDecimal("12.5"), actual);
	}

	@Test
	void testMutiplyBigDecimalBigDecimal() {
		BasicMath basic = new BasicMath();

		BigDecimal actual = basic.mutiply(new BigDecimal("5"), new BigDecimal("2.5"));

		Assertions.assertEquals(new BigDecimal("12.5"), actual);
	}

	@Test
	void testMutiplyPrecision2() {
		BasicMath basic = new BasicMath();
		double actual = basic.mutiplyPrecision2(0.11d, 0.22d);
		Assertions.assertEquals(0.02d, actual, 0.001d);
	}

	@Test
	void testMutiplyPrecision3() {
		BasicMath basic = new BasicMath();
		double actual = basic.mutiplyPrecision3(0.111d, 0.222d);
		Assertions.assertEquals(0.025d, actual, 0.001d);
	}

	@Test
	void testMutiplyPrecision4() {
		BasicMath basic = new BasicMath();
		double actual = basic.mutiplyPrecision4(0.1111d, 0.2222d);
		Assertions.assertEquals(0.0247d, actual, 0.001d);
	}

	@Test
	void testMutiplyPrecision5() {
		BasicMath basic = new BasicMath();
		double actual = basic.mutiplyPrecision5(0.11111d, 0.22222d);
		Assertions.assertEquals(0.02469d, actual, 0.001d);
	}

	@Test
	void testMutiplyPrecision6() {
		BasicMath basic = new BasicMath();
		double actual = basic.mutiplyPrecision6(0.111111d, 0.222222d);
		Assertions.assertEquals(0.024691d, actual, 0.001d);
	}

	@Test
	void testMutiplyPrecision7() {
		BasicMath basic = new BasicMath();
		double actual = basic.mutiplyPrecision7(0.1111111d, 0.2222222d);
		Assertions.assertEquals(0.0246914d, actual, 0.001d);
	}

	@Test
	void testMutiplyPrecision8() {
		BasicMath basic = new BasicMath();
		double actual = basic.mutiplyPrecision8(0.11111111d, 0.22222222d);
		Assertions.assertEquals(0.02469136d, actual, 0.001d);
	}
}
