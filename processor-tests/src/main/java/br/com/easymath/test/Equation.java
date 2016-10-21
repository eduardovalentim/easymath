package br.com.easymath.test;

import java.math.BigDecimal;

import br.com.easymath.annotations.Formula;
import br.com.easymath.annotations.MathContext;

public abstract class Equation {

	@Formula(value = "x * (x + x)", context=@MathContext(precision=4))
	public abstract BigDecimal simplicity(BigDecimal... args);
}
