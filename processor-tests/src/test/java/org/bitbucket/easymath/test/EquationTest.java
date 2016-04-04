package org.bitbucket.easymath.test;

import java.math.BigDecimal;

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
		BigDecimal actual = formulas.simple(BigDecimal.ONE);
		Assert.assertEquals(new BigDecimal("2"), actual);
	}

	@Test
	public void testQuadric() {
		BigDecimal actual = formulas.quadric(1, 2.5, 3, 4.2);
		Assert.assertEquals(new BigDecimal("15.7"), actual);
	}

	@Test
	public void testCubic() {
		BigDecimal actual = formulas.cubic(5, 7);
		Assert.assertEquals(new BigDecimal("3.3E+2"), actual);
	}

	@Test
	public void testQuartic() {
		BigDecimal actual = formulas.quartic(1.5, 2);
		Assert.assertEquals(new BigDecimal("7E+1"), actual);
	}

}
