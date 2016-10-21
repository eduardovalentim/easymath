package br.com.easymath.processor.mathematical;

import org.junit.Before;
import org.junit.Test;

import br.com.easymath.annotations.Formula;
import br.com.easymath.processor.mathematical.MathematicalProcessor;

@Formula(name="f", value="a + 2 * c / d")
public class MathematicalProcessorTest {

	private Formula formula;

	@Before
	public void setUp() throws Exception {
		formula = getClass().getAnnotation(Formula.class);
	}

	@Test
	public void testCompile() {
		MathematicalProcessor processor = new MathematicalProcessor();
		//processor.compile(formula);
	}

}
