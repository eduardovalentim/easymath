package org.bitbucket.easymath.test;

import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.Mathematical;
import org.bitbucket.easymath.annotations.NumberType;

@Mathematical(functions = {
        @Function(name = "squareDiff", formula = "a ^ 2 - b ^2"),
        @Function(name = "cubeDiff", formula = "a ^ 3 - b ^ 3", using = NumberType.DOUBLE),
        @Function(name = "sumOfCubes", formula = "a * 3 + b * 3", using = NumberType.BIGDECIMAL),
        @Function(name = "sumProgression", formula = "{(n / 2) * [2 * a + (n - 1) * d]}", using = NumberType.BIGDECIMAL),
        @Function(name = "functionCall", formula = "call(call(a ^ 2) - call(b ^ squareDiff(a, b)))")})
public class TestA {

}
