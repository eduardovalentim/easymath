package com.github.eduardovalentim.easymath.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.eduardovalentim.easymath.utils.SystemUtils;

/**
 * To run this test class in your IDE configure a environment variable LD_LIBRARY_PATH pointing to 
 * target/nar/${project.artifactId}-${project.version}-${nar.aol}/lib/${nar.aol}/shared
 * 
 * @author valentim
 */
class NativeAlgebraIT {

	static {
		System.setProperty(SystemUtils.LIBRARY_NAME, "easymath-processor-native");
	}
	
	@Test
	void testSquaresDifference() {
		double actual = NativeAlgebraLibrary.cubesDifference(3, 6);
		double expected = NativeAlgebraLibrary.cubesDifferenceExpansion(3, 6);
		Assertions.assertEquals(expected, actual);
	}
}
