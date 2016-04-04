package org.bitbucket.easymath;

import java.math.BigDecimal;
import java.math.MathContext;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

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

	public BigDecimal pow(BigDecimal base, BigDecimal exponent, MathContext mc) {
		if (base == null) {
			throw new IllegalArgumentException("Argument 'base' cannot be null.");
		}
		if (exponent == null) {
			throw new IllegalArgumentException("Argument 'exponent' cannot be null.");
		}
		if (mc == null) {
			throw new IllegalArgumentException("Argument 'mc' cannot be null.");
		}

		Apfloat result = ApfloatMath.pow(new Apfloat(base), new Apfloat(exponent));
		return new BigDecimal(result.toString(true), mc);
	}

	public double pow(double base, double exponent) {
		return Math.pow(base, exponent);
	}

	public BigDecimal fat(BigDecimal n, MathContext mc) {
		if (n == null) {
			throw new IllegalArgumentException("Argument 'n' cannot be null.");
		}
		if (mc == null) {
			throw new IllegalArgumentException("Argument 'mc' cannot be null.");
		}
		
		return BigDecimal.ZERO;
	}

	public BigDecimal fat(BigDecimal n) {
		if (n == null) {
			throw new IllegalArgumentException("Argument 'n' cannot be null.");
		}

		return BigDecimal.ZERO;
	}

	public double fat(double n) {
		return 0D;
	}

	public double typecast(Number n) {
		if (n == null) {
			throw new IllegalArgumentException("Argument 'n' cannot be null.");
		}
		
    	return n.doubleValue();
    }
	
	public BigDecimal typecast(Number n, MathContext mc) {
		if (n == null) {
			throw new IllegalArgumentException("Argument 'n' cannot be null.");
		}
		if (mc == null) {
			throw new IllegalArgumentException("Argument 'mc' cannot be null.");
		}
		
    	BigDecimal result = null;
    	
    	if (n instanceof BigDecimal) {
    		result = ((BigDecimal) n).round(mc);
    	} else {
    		result = new BigDecimal(n.toString(), mc);
    	}
    	
    	return result;
    }

	@SuppressWarnings("unchecked")
	public <T> T compute(String functionName, MathContext mc, Number... args) {
		Function function = catalog.getFunction(functionName);
		return (T) function.compute(mc, args);
	}
}
