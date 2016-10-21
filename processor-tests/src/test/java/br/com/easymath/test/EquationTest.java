package br.com.easymath.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EquationTest {

	private EquationMath formulas;
	
	@Before
	public void before() {
		formulas = new EquationMath();
	}
	
	@Test
	public void testSimple() {
		BigDecimal actual = formulas.simple(BigDecimal.ONE);
		Assert.assertTrue(new BigDecimal("2").compareTo(actual) == 0);
	}

	@Test
	public void testQuadric() {
		BigDecimal actual = formulas.quadric(1, 2.5, 3, 4.2);
		Assert.assertTrue(new BigDecimal("15.7").compareTo(actual) == 0);
	}

	@Test
	public void testCubic() {
		Double actual = formulas.cubic(5, 7);
		Assert.assertTrue(new Double("386.0").compareTo(actual) == 0);
	}

	@Test
	public void testQuartic() {
		BigDecimal actual = formulas.quartic(1, 1);
		Assert.assertTrue(new BigDecimal("1e1").compareTo(actual) == 0);
	}

}
