package com.github.eduardovalentim.easymath.utils;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.github.eduardovalentim.easymath.Numbers;

class ClassUtilsTest {

	@Test
	void testIsValidJavaIdentifierWithNull() {
		boolean actual = ClassUtils.isValidJavaIdentifier(null);
		Assertions.assertFalse(actual);
	}

	@Test
	void testIsValidJavaIdentifierWithEmpty() {
		boolean actual = ClassUtils.isValidJavaIdentifier("");
		Assertions.assertFalse(actual);
	}

	@ParameterizedTest
	@ValueSource(strings = {"a", "_", "_1", "identifier"})
	void testIsValidJavaIdentifierWithLetter(String value) {
		boolean actual = ClassUtils.isValidJavaIdentifier(value);
		Assertions.assertTrue(actual);
	}

	@ParameterizedTest
	@ValueSource(strings = {"1", "int", "_1 "})
	void testIsValidJavaIdentifierWithNumber() {
		boolean actual = ClassUtils.isValidJavaIdentifier("1");
		Assertions.assertFalse(actual);
	}

	@Test
	void testGetNumericMethodsWithNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			ClassUtils.getNumericMethods(null);
		});
	}

	@Test
	void testGetNumericMethodsWithJavaMath() {
		List<Method> methods = ClassUtils.getNumericMethods(Numbers.class);
		Assertions.assertEquals(2, methods.size());
	}

}
