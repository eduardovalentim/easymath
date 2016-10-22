package br.com.easymath.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import br.com.easymath.FunctionCatalog;
import br.com.easymath.test.functions.CosFunction;
import br.com.easymath.test.functions.SinFunction;

public class TrigonometryMathTest {

	static FunctionCatalog catalog = FunctionCatalog.valueOf(SinFunction.INSTANCE, CosFunction.INSTANCE);

	@Test
	public void testSinABNumberArray() {
		TrigonometryMath trigonometry = new TrigonometryMath(catalog);
	
		BigDecimal actual = trigonometry.sinAB(45, 70);
		double expected = trigonometry.sinABExpansion(45d, 70d);
		
		Assert.assertEquals(expected, actual.doubleValue(), 0.001);
	}

}
