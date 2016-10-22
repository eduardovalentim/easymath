package br.com.easymath.test;

import java.math.BigDecimal;

import br.com.easymath.annotations.Formula;

public abstract class Algebra {

    /**
     * 
     * @param args
     * @return
     */
	@Formula("(a ^ 2) - (b ^ 2)")
	public abstract double squaresDifference(Number... args);

    /**
     * 
     * @param args
     * @return
     */
	@Formula("(a - b) * (a + b)")
	public abstract BigDecimal squaresDifferenceExpansion(Number... args);

    /**
     * 
     * @param args
     * @return
     */
	@Formula("a ^ 3 - b ^ 3")
	public abstract double cubesDifference(Number... args);

    /**
     * 
     * @param args
     * @return
     */
	@Formula("(a - b) * (a ^ 2 + a * b + b ^ 2)")
	public abstract BigDecimal cubesDifferenceExpansion(Number... args);

    /**
     * 
     * @param args
     * @return
     */
	@Formula("(a ^ 3) + (b ^ 3)")
	public abstract double cubesSum(Number... args);

    /**
     * 
     * @param args
     * @return
     */
	@Formula("(a + b) * ((a ^ 2) - (a * b) + (b ^ 2))")
	public abstract BigDecimal cubesSumExpansion(Number... args);
}
