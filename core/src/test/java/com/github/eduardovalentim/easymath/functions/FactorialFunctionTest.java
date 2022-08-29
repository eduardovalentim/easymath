package com.github.eduardovalentim.easymath.functions;

import java.math.BigInteger;
import java.math.MathContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FactorialFunctionTest {

	/**
	 * Factorial method test
	 */
	@ParameterizedTest
	@CsvSource({
		"-6,-720",
		"-5,-120",
		"-4,-24",
		"-3,-6",
		"-2,-2",
		"-1,-1",
		"0,0",
		"1,1",
		"2,2",
		"3,6",
		"4,24",
		"5,120",
		"6,720",
		"127,3012660018457659544809977077527059692324164918673621799053346900596667207618480809067860692097713761984609779945772783965563851033300772326297773087851869982500270661791244122597621760000000000000000000000000000000"
	})
	void testFactorial(Long input, BigInteger expected) {
		FactorialFunction factorial = new FactorialFunction();
		BigInteger actual = factorial.perform(MathContext.DECIMAL32, input);
		
		Assertions.assertEquals(expected, actual);
	}
}
