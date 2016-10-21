package br.com.easymath.test;

import java.math.BigDecimal;

import br.com.easymath.annotations.Formula;

/**
 * @author eduardo.valentim
 */
public abstract class Equation {

    /**
     * 
     * @param args
     * @return
     */
	@Formula(value = "x * (x + x)", precision=4)
	public abstract BigDecimal simplicity(Number... args);
}
