package com.github.eduardovalentim.easymath.processor.mathematical.utils;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.eduardovalentim.easymath.processor.mathematical.operation.operand.InputOperand;

public class FormatUtilsTest {

	private static FormatUtils format = FormatUtils.INSTANCE;
	
	@Test
	public void testInputFormatVariables() {
		Collection<InputOperand> inputs = new ArrayList<>();
		inputs.add(new InputOperand("double", "a"));
		inputs.add(new InputOperand("double", "lata"));
		
		String actual = format.formula("a + lata", inputs, 7, 0);
		
		Assertions.assertEquals("{0, number, #.#######} + {1, number, #.#######}", actual);
	}

	@Test
	public void testInputFormatFunctions() {
		Collection<InputOperand> inputs = new ArrayList<>();
		inputs.add(new InputOperand("double", "alpha"));
		inputs.add(new InputOperand("double", "beta"));
		
		String actual = format.formula("sin(alpha) * cos(beta) + sin(beta) * cos(alpha)", inputs, 7, 0);
		
		Assertions.assertEquals("sin({0, number, #.#######}) * cos({1, number, #.#######}) + sin({1, number, #.#######}) * cos({0, number, #.#######})", actual);
	}

	
}
