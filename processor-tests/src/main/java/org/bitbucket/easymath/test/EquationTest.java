package org.bitbucket.easymath.test;

import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.Mathematical;

@Mathematical(functions = {
        @Function(name = "simple", formula = "m * (x + b)"),
        @Function(name = "quadric", formula = "a * x ^ 2 + b * x + c"),
        @Function(name = "cubic", formula = "x ^ 3 + a * 1 * x ^ 2 + a * 2 * x + a * 3"),
        @Function(name = "quartic", formula = "x ^ 4 + a * 1 * x ^ 3 + a * 2 * x ^ 2 + a * 3 * x + a * 4"),
        })
public class EquationTest {

}
