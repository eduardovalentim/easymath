package br.com.easymath.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.RoundingMode;

/**
 * Annotation to decribe mathematical function
 * 
 * @author eduardovalentim
 */
@Inherited
@Documented
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
public @interface Formula {

	/**
	 * Text to describe the formula
	 * 
	 * @return The value of this annotation attribute
	 */
	public String value();

	/**
	 * The number of digits to be used for an operation. A value of 0 indicates
	 * that unlimited precision (as many digits as are required) will be used.
	 * Note that leading zeros (in the coefficient of a number) are never
	 * significant.
	 *
	 * <p>
	 * {@code precision} will always be non-negative.
	 * 
	 * @return The value of this annotation attribute
	 */
	public int precision() default 7;

	/**
	 * The rounding algorithm to be used for an operation.
	 * 
	 * @return The value of this annotation attribute
	 */
	public RoundingMode roundingMode() default RoundingMode.HALF_UP;
}
