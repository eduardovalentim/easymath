package org.bitbucket.easymath.processor;

import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.Function.NumberType;
import org.bitbucket.easymath.annotations.Mathematical;

@Mathematical(functions = { @Function(name = "juros", formula = "x + -2.0 * y / 5 ^ 6.2", using = NumberType.BIGDECIMAL),
		@Function(name = "espaco", formula = "2.5 ^ a! ^ 7 / 2 / b", using = NumberType.DOUBLE),
		@Function(name = "par", formula = "((2 + 1) * 5) ^ 0.5 / 2", using = NumberType.DOUBLE) })
public class Test {

	public void solve() {
	}
}
