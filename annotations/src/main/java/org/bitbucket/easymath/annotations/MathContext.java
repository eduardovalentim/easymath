package org.bitbucket.easymath.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.RoundingMode;

/**
 * Annotation created to represent the class <code>java.math.MathContext</code>
 * allowing programmers to define the precision and rounding mode of a function.
 * 
 * @author Eduardo.Valentim
 * 
 * @see java.math.MathContext
 * @see java.math.RoundingMode;
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MathContext {

	/**
	 * The number of digits to be used for an operation. A value of 0 indicates
	 * that unlimited precision (as many digits as are required) will be used.
	 * Note that leading zeros (in the coefficient of a number) are never
	 * significant.
	 *
	 * <p>
	 * {@code precision} will always be non-negative.
	 *
	 * @serial
	 */
	public int precision() default 9;

	/**
	 * The rounding algorithm to be used for an operation.
	 *
	 * @see RoundingMode
	 * @serial
	 */
	public RoundingMode roundingMode() default RoundingMode.HALF_UP;

}
