package org.bitbucket.easymath.test.inner;

import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.Mathematical;
import org.bitbucket.easymath.annotations.NumberType;

@Mathematical(functions = {
        @Function(name = "squarediff", formula = " (a - b) * (a + b)"),
        @Function(name = "cubediff", formula = "(a - b) * (a * 2 + a * b + b * 2) ", using = NumberType.DOUBLE),
        @Function(name = "infinitySum", formula = "a / (1 - r)", using = NumberType.BIGDECIMAL) })
public class TestB {

}
