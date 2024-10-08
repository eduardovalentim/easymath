package com.github.eduardovalentim.easymath.test.functions;

import java.math.MathContext;

public class SinFunction extends AbstractTrigonometryFunction {

	public SinFunction() {
		super();
	}
	
	@Override
	public String name() {
		return "sin";
	}

	@Override
	public Double perform(MathContext mc, Number... inputs) {
		return Math.sin(super.perform(mc, inputs));
	}

}
