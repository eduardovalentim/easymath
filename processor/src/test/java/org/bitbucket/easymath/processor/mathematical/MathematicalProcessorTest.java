package org.bitbucket.easymath.processor.mathematical;

import org.bitbucket.easymath.annotations.Function;
import org.junit.Before;
import org.junit.Test;

@Function(name="f", formula="a + 2 * c / d")
public class MathematicalProcessorTest {

	private Function formula;

	@Before
	public void setUp() throws Exception {
		formula = getClass().getAnnotation(Function.class);
	}

	@Test
	public void testCompile() {
		MathematicalProcessor processor = new MathematicalProcessor();
		//processor.compile(formula);
	}

}
