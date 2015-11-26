package org.bitbucket.easymath.test;

import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.MathContext;
import org.bitbucket.easymath.annotations.Mathematical;
import org.bitbucket.easymath.annotations.NumberType;

// FIXME formula = "1" -> getLastOperation = NoSuchElementException
@Mathematical(functions = {
        @Function(name = "pow$bd", formula = "1 ^ 2", context=@MathContext(precision=3)),
        @Function(name = "mul$bd", formula = "1 * 3", context=@MathContext(precision=3)),
        @Function(name = "div$bd", formula = "1 / 4", context=@MathContext(precision=3)),
        @Function(name = "mod$bd", formula = "1 % 5", context=@MathContext(precision=3)),
        @Function(name = "add$bd", formula = "1 + 6", context=@MathContext(precision=3)),
        @Function(name = "sub$bd", formula = "1 - 7", context=@MathContext(precision=3)),
        @Function(name = "fat$bd", formula = "1!", context=@MathContext(precision=3)),
        @Function(name = "pow$d", formula = "1 ^ 2", using=NumberType.DOUBLE, context=@MathContext(precision=2)),
        @Function(name = "mul$d", formula = "1 * 3", using=NumberType.DOUBLE, context=@MathContext(precision=2)),
        @Function(name = "div$d", formula = "1 / 4", using=NumberType.DOUBLE, context=@MathContext(precision=2)),
        @Function(name = "mod$d", formula = "1 % 5", using=NumberType.DOUBLE, context=@MathContext(precision=2)),
        @Function(name = "add$d", formula = "1 + 6", using=NumberType.DOUBLE, context=@MathContext(precision=2)),
        @Function(name = "sub$d", formula = "1 - 7", using=NumberType.DOUBLE, context=@MathContext(precision=2)),
        @Function(name = "fat$d", formula = "1!", using=NumberType.DOUBLE, context=@MathContext(precision=2)),
        
        @Function(name = "pow$bd$i", formula = "a ^ b", context=@MathContext(precision=1)),
        @Function(name = "mul$bd$i", formula = "a * b", context=@MathContext(precision=1)),
        @Function(name = "div$bd$i", formula = "a / b", context=@MathContext(precision=1)),
        @Function(name = "mod$bd$i", formula = "a % b", context=@MathContext(precision=1)),
        @Function(name = "add$bd$i", formula = "a + b", context=@MathContext(precision=1)),
        @Function(name = "sub$bd$i", formula = "a - b", context=@MathContext(precision=1)),
        @Function(name = "fat$bd$i", formula = "a!", context=@MathContext(precision=1)),
        @Function(name = "pow$d$i", formula = "a ^ b", using=NumberType.DOUBLE, context=@MathContext(precision=0)),
        @Function(name = "mul$d$i", formula = "a * b", using=NumberType.DOUBLE, context=@MathContext(precision=0)),
        @Function(name = "div$d$i", formula = "a / b", using=NumberType.DOUBLE, context=@MathContext(precision=0)),
        @Function(name = "mod$d$i", formula = "a % b", using=NumberType.DOUBLE, context=@MathContext(precision=0)),
        @Function(name = "add$d$i", formula = "a + b", using=NumberType.DOUBLE, context=@MathContext(precision=0)),
        @Function(name = "sub$d$i", formula = "a - b", using=NumberType.DOUBLE, context=@MathContext(precision=0)),
        @Function(name = "fat$d$i", formula = "a!", using=NumberType.DOUBLE, context=@MathContext(precision=0))
        })
public class BasicMathTest {

}
