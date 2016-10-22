package br.com.easymath.test;

import java.math.BigDecimal;

import br.com.easymath.annotations.Formula;

/**
 * @author eduardo.valentim
 */
public abstract class Basic {

    /**
     * 
     * @param args
     * @return
     */
	@Formula("a + b")
	public abstract double add(Number... args);

    /**
     * 
     * @param args
     * @return
     */
	@Formula("a - b")
	public abstract Double subtract(Number... args);

	/**
     * 
     * @param args
     * @return
     */
	@Formula("a / b")
	public abstract BigDecimal divide(Number... args);

    /**
     * 
     * @param args
     * @return
     */
	@Formula("a * b")
	public abstract BigDecimal mutiply(Number... args);

}
