package org.bitbucket.easymath.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasicMathTest {

	private BasicMathFormulas formulas;
	
	@Before
	public void before() {
		formulas = new BasicMathFormulas();
	}
	
	@Test
	public void add$bd() {
		BigDecimal actual = formulas.add$bd();
		Assert.assertEquals(new BigDecimal("7"), actual);
	}

	@Test
	public void add$bd$i() {
		formulas.add$bd$i(3, 5);
	}

	@Test
	public void add$d() {
		formulas.add$d();
	}

	@Test
	public void add$d$i() {
		formulas.add$bd$i(0.2, 0.4);
	}

	@Test
	public void div$bd() {
		formulas.div$bd();
	}

	@Test
	public void div$bd$i() {
		formulas.div$bd$i(3, 6);
	}

	@Test
	public void div$d() {
		formulas.div$d();
	}

	@Test
	public void div$d$i() {
		formulas.div$d$i(10, 10.2);
	}

	@Test
	public void fat$bd() {
		formulas.fat$bd();
	}

	@Test
	public void fat$bd$i() {
		formulas.fat$bd$i(3);
	}

	@Test
	public void fat$d() {
		formulas.fat$d();
	}

	@Test
	public void fat$d$i() {
		formulas.fat$d$i(3);
	}

	@Test
	public void mod$bd() {
		formulas.mod$bd();
	}

	@Test
	public void mod$bd$i() {
		formulas.mod$bd$i(2, 3);
	}

	@Test
	public void mod$d() {
		formulas.mod$d();
	}

	@Test
	public void mod$d$i() {
		formulas.mod$d$i(23.4, 23.4);
	}

	@Test
	public void mul$bd() {
		formulas.mul$bd();
	}

	@Test
	public void mul$bd$i() {
		formulas.mul$bd$i(10.2345, 3.876);
	}

	@Test
	public void mul$d() {
		formulas.mul$d();
	}

	@Test
	public void mul$d$i() {
		formulas.mul$d$i(12.3, 45.7);
	}

	@Test
	public void pow$bd() {
		formulas.pow$bd();
	}

	@Test
	public void pow$bd$i() {
		formulas.pow$bd$i(12, 33.4);
	}

	@Test
	public void pow$d() {
		formulas.pow$d();
	}

	@Test
	public void pow$d$i() {
		formulas.pow$d$i(3, 5);
	}

	@Test
	public void sub$bd() {
		formulas.sub$bd$i(1, 2);
	}

	@Test
	public void sub$bd$i() {
		formulas.sub$bd$i(2, 3);
	}

	@Test
	public void sub$d() {
		formulas.sub$d();
	}

	@Test
	public void sub$d$i() {
		formulas.sub$d$i(1, 2);
	}
}
