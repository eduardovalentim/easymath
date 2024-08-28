package com.github.eduardovalentim.easymath.processor.mathematical.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.eduardovalentim.easymath.processor.mathematical.operation.BinaryOperation;
import com.github.eduardovalentim.easymath.processor.mathematical.operation.FunctionOperation;
import com.github.eduardovalentim.easymath.processor.mathematical.operation.Operation;
import com.github.eduardovalentim.easymath.processor.mathematical.operation.UnaryOperation;
import com.github.eduardovalentim.easymath.processor.mathematical.operation.operand.InputOperand;
import com.github.eduardovalentim.easymath.processor.mathematical.operation.operand.Operand;

class FormatUtilsTest {

	private static FormatUtils format = new FormatUtils();
	
	@Test
	void testInputFormatVariables() {
		Collection<InputOperand> inputs = new ArrayList<>();
		inputs.add(new InputOperand("double", "a"));
		inputs.add(new InputOperand("double", "lata"));
		
		String actual = format.formula("a + lata", inputs, 7, 0);
		
		Assertions.assertEquals("{0, number, #.#######} + {1, number, #.#######}", actual);
	}

	@Test
	void testInputFormatFunctions() {
		Collection<InputOperand> inputs = new ArrayList<>();
		inputs.add(new InputOperand("double", "alpha"));
		inputs.add(new InputOperand("double", "beta"));
		
		String actual = format.formula("sin(alpha) * cos(beta) + sin(beta) * cos(alpha)", inputs, 7);
		
		Assertions.assertEquals("sin({0, number, #.##########}) * cos({1, number, #.##########}) + sin({1, number, #.##########}) * cos({0, number, #.##########})", actual);
	}

	@Test
	void testInputFormatFunctionsWithNull() {
		Collection<InputOperand> inputs = new ArrayList<>();
		Assertions.assertThrows(IllegalStateException.class, () -> {
			format.formula(null, inputs, 7, 0);
		});
	}

	@Test
	void testInputFormatFunctionsWithEmpty() {
		Collection<InputOperand> inputs = new ArrayList<>();
		Assertions.assertThrows(IllegalStateException.class, () -> {
			format.formula("", inputs, 7, 0);
		});
	}

	@Test
	void testInputFormatFunctionsWithBlank() {
		Collection<InputOperand> inputs = new ArrayList<>();
		Assertions.assertThrows(IllegalStateException.class, () -> {
			format.formula("   ", inputs, 7);
		});
	}

	@Test
	void testInputFormatFunctionsWithNullInput() {
		Assertions.assertThrows(IllegalStateException.class, () -> {
			format.formula("x + 1", null, 7, 3);
		});
	}

	@Test
	void testInputFormatFunctionsWithNegativePrecision() {
		Collection<InputOperand> inputs = new ArrayList<>();
		Assertions.assertThrows(IllegalStateException.class, () -> {
			format.formula("x + 1", inputs, -7);
		});
	}

	@Test
	void testInputFormatFunctionsWithEmptyInputs() {
		Collection<InputOperand> inputs = new ArrayList<>();
		String actual = format.formula("x + 1", inputs, 7, 0);
		Assertions.assertEquals("x + 1", actual);
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "   "})
	void testOperationWithInvalidTexts(final String value) {
		Operand operand = new InputOperand("double", "x");
		Operation operation = new UnaryOperation("x", "double", operand, "!", "x!");
		Assertions.assertThrows(IllegalStateException.class, () -> {
			format.operation(value, operation, 7);
		});
	}

	@Test
	void testOperationWithNullText() {
		Operand operand = new InputOperand("double", "x");
		Operation operation = new UnaryOperation("x", "double", operand, "!", "x!");
		Assertions.assertThrows(IllegalStateException.class, () -> {
			format.operation(null, operation, 7);
		});
	}

	@Test
	void testOperationWithNullOperation() {
		Assertions.assertThrows(IllegalStateException.class, () -> {
			format.operation("a!", null, 7);
		});
	}

	@Test
	void testOperationWithNegativePrecision() {
		Operand operand = new InputOperand("double", "x");
		Operation operation = new UnaryOperation("x", "double", operand, "!", "x!");
		Assertions.assertThrows(IllegalStateException.class, () -> {
			format.operation("a!", operation, -7);
		});
	}

	@Test
	void testOperationUnary() {
		Operand operand = new InputOperand("double", "x");
		Operation operation = new UnaryOperation("x", "double", operand, "!", "x!");
		String actual = format.operation("a!", operation, 7);
		Assertions.assertEquals("{0, number, #.##########} = {1, number, #.##########}", actual);
	}

	@Test
	void testOperationBinary() {
		Operand leftOperand = new InputOperand("double", "a");
		Operand rightOperand = new InputOperand("double", "b");
		Operation operation = new BinaryOperation("r0", "double", leftOperand, "*", rightOperand, "a + b");
		String actual = format.operation("a!", operation, 7);
		Assertions.assertEquals("{0, number, #.##########}*{1, number, #.##########} = {2, number, #.##########}", actual);
	}

	@Test
	void testOperationFunction() {
		Operand aOperand = new InputOperand("double", "a");
		Operand bOperand = new InputOperand("double", "b");
		Deque<Operand> operands = new LinkedList<>();
		operands.add(aOperand);
		operands.add(bOperand);
		Operation operation = new FunctionOperation("r0", "func", "double", operands , "func(a, b)");
		String actual = format.operation("a!", operation, 7);
		Assertions.assertEquals("func({0, number, #.##########}, {1, number, #.##########}) = {2, number, #.##########}", actual);
	}

	@Test
	void testOperationAnonymous() {
		Operation operation = new Operation() {
			@Override
			public String getId() {
				return null;
			}

			@Override
			public String getOperator() {
				return null;
			}

			@Override
			public String getType() {
				return null;
			}

			@Override
			public String getText() {
				return null;
			}
		};
		String actual = format.operation("a!", operation, 7);
		Assertions.assertEquals(" = {0, number, #.##########}", actual);
	}

	@Test
	void testOutput() {
		String actual = format.output(0, 7);
		Assertions.assertEquals("{0, number, #.##########}", actual);
	}
}
