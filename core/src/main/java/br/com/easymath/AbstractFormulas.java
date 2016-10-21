package br.com.easymath;

import java.math.BigDecimal;
import java.math.MathContext;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

/**
 * 
 * @author Eduardo.Valentim
 *
 */
public abstract class AbstractFormulas {

	private FunctionCatalog catalog;

	public AbstractFormulas(FunctionCatalog catalog) {
		if (catalog == null) {
			throw new IllegalArgumentException("Argument 'catalog' cannot be null.");
		}

		this.catalog = catalog;
	}

	public <T extends Number> T pow(Number base, Number exponent, MathContext mc, Class<T> type) {
		if (base == null) {
			throw new IllegalArgumentException("Argument 'base' cannot be null.");
		}
		if (exponent == null) {
			throw new IllegalArgumentException("Argument 'exponent' cannot be null.");
		}
		if (mc == null) {
			throw new IllegalArgumentException("Argument 'mc' cannot be null.");
		}
		if (type == null) {
			throw new IllegalArgumentException("Argument 'type' cannot be null.");
		}

		Apfloat result = ApfloatMath.pow(new Apfloat(base.toString()), new Apfloat(exponent.toString()));
		result = ApfloatMath.round(result, mc.getPrecision(), mc.getRoundingMode());

		return typecast(result, type);
	}

	public <T extends Number> T fat(Number n, Class<T> type) {
		if (n == null) {
			throw new IllegalArgumentException("Argument 'n' cannot be null.");
		}
		if (type == null) {
			throw new IllegalArgumentException("Argument 'type' cannot be null.");
		}

		Apint result = ApintMath.factorial(n.longValue());

		return typecast(result, type);
	}

	@SuppressWarnings("unchecked")
	protected <T extends Number> T typecast(Number n, Class<T> type) {
		if (n == null) {
			throw new IllegalArgumentException("Argument 'n' cannot be null.");
		}

		if (type == null) {
			throw new IllegalArgumentException("Argument 'type' cannot be null.");
		}

		Object value = null;

		if (type.isInstance(n)) {
			value = n;
		} else {
			if (type.isAssignableFrom(Double.class) || type.isAssignableFrom(double.class)) {
				value = n.doubleValue();
			} else if (type.isAssignableFrom(BigDecimal.class)) {
				value = new BigDecimal(n.toString());
			} else if (type.isAssignableFrom(Apfloat.class)) {
				value = new Apfloat(n.toString());
			} else {
				throw new IllegalStateException(String.format("Typecast from '%s' to '%s' is not supported.",
						n.getClass().getSimpleName(), type.getSimpleName()));
			}
		}

		return (T)value;
	}

	protected <T extends Number> T peform(String functionName, Number... inputs) {
		return catalog.peform(functionName, inputs);
	}
}
