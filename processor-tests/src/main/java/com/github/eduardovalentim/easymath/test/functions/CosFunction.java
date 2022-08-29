package com.github.eduardovalentim.easymath.test.functions;

import java.math.MathContext;

public class CosFunction extends AbstractTrigonometryFunction {

	public static final CosFunction INSTANCE = new CosFunction();
	
	@Override
	public String name() {
		return "cos";
	}

	@Override
	public Double perform(MathContext mc, Number... inputs) {
		return Math.cos(super.perform(mc, inputs));
	}

}
