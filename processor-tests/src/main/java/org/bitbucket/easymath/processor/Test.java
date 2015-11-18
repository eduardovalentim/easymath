package org.bitbucket.easymath.processor;

import java.math.RoundingMode;

import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.MathContext;
import org.bitbucket.easymath.annotations.Mathematical;
import org.bitbucket.easymath.annotations.NumberType;

@Mathematical(functions = {
        @Function(name = "juros", formula = "x + -2.0 * y / +5 ^ x", using = NumberType.BIGDECIMAL, context = @MathContext(precision = 2, roundingMode = RoundingMode.CEILING)),
        @Function(name = "espaco", formula = "2.5 ^ a! ^ 7 / a / b", using = NumberType.BIGDECIMAL),
        @Function(name = "par", formula = "(2 + (1 * 5)) ^ {0.5 / [2 * -1]}", using = NumberType.BIGDECIMAL) })
public class Test {

    public void solve() {
    }
}
