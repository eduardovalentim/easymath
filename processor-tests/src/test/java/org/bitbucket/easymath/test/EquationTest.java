package org.bitbucket.easymath.test;

import java.math.BigDecimal;

import org.apfloat.Apfloat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EquationTest {

	private EquationFormulas formulas;
	
	@Before
	public void before() {
		formulas = new EquationFormulas();
	}
	
	@Test
	public void testSimple() {
		Apfloat actual = formulas.simple(BigDecimal.ONE);
		Assert.assertEquals(new Apfloat("2"), actual);
	}

	@Test
	public void testQuadric() {
		BigDecimal actual = formulas.quadric(1, 2.5, 3, 4.2);
		Assert.assertEquals(new BigDecimal("15.7"), actual);
	}

	@Test
	public void testCubic() {
		Double actual = formulas.cubic(5, 7);
		Assert.assertEquals(new Double("386.0"), actual);
	}

	@Test
	public void testQuartic() {
		Apfloat actual = formulas.quartic(1, 1);
		Assert.assertEquals(new Apfloat("1e1"), actual);
	}

}
