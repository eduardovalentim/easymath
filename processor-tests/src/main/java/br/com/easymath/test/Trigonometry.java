package br.com.easymath.test;

import java.math.BigDecimal;

import br.com.easymath.annotations.Formula;

public abstract class Trigonometry {

    /**
     * 
     * @param args
     * @return
     */
	@Formula("sin(alpha + beta)")
	public abstract BigDecimal sinAB(Number... args);

    /**
     * 
     * @param args
     * @return
     */
	@Formula("sin(alpha) * cos(beta) + sin(beta) * cos(alpha)")
	public abstract double sinABExpansion(Number... args);
}
