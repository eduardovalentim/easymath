package org.bitbucket.easymath.test;

import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.MathContext;
import org.bitbucket.easymath.annotations.Mathematical;

@Mathematical(functions = {
        @Function(name = "simple", formula = "m * (x + b)", context=@MathContext(precision=4)),
        @Function(name = "quadric", formula = "a * x ^ 2 + b * x + c", context=@MathContext(precision=3)),
        @Function(name = "cubic", formula = "x ^ 3 + a * 1 * x ^ 2 + a * 2 * x + a * 3", context=@MathContext(precision=2)),
        @Function(name = "quartic", formula = "(x ^ 4) + (a * 1 * x ^ 3) + (a * 2 * x ^ 2) + (a * 3 * x + a * 4)", context=@MathContext(precision=1)),
        })
public class EquationTest {

}
