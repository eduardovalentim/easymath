package com.github.eduardovalentim.easymath.test;

import java.math.BigDecimal;

import com.github.eduardovalentim.easymath.annotations.EasyMath;
import com.github.eduardovalentim.easymath.annotations.Formula;

@EasyMath
public interface Constants {

	@Formula("2546 / 76.4567 + 36456 / 86755")
	public abstract BigDecimal adding(Number... inputs);

	@Formula("((2546 * 86755) + (76.4567 * 36456)) / (76.4567 * 86755)")
	public abstract BigDecimal addingExpansion(Number... inputs);
	
	@Formula("2546.789789 / 76.4567 - 0.36456 / 8.6755")
	public abstract BigDecimal subtracting(Number... inputs);

	@Formula("((2546.789789 * 8.6755) - (76.4567 * 0.36456)) / (76.4567 * 8.6755)")
	public abstract BigDecimal subtractingExpansion(Number... inputs);
	
}
