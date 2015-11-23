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
        this.catalog = catalog;
    }
    
    public BigDecimal pow(BigDecimal base, BigDecimal exponent, MathContext mc) {
        Apfloat result = ApfloatMath.pow(new Apfloat(base), new Apfloat(exponent));
        return new BigDecimal(result.toString(true), mc);
    }
    
    public double pow(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public BigDecimal fat(BigDecimal n, MathContext mc) {
        return BigDecimal.ZERO;
    }

    public BigDecimal fat(BigDecimal n) {
        return BigDecimal.ZERO;
    }

    public double fat(double n) {
        return 0D;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T compute(String functionName, MathContext mc, Number... args) {
        Function function = catalog.getFunction(functionName);
        return (T) function.compute(mc, args);
    }
}
