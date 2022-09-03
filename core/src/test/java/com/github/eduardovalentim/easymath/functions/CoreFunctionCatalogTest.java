package com.github.eduardovalentim.easymath.functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CoreFunctionCatalogTest {

	@Test
	void test() {
		// creating a mock instance
		CoreFunctionCatalog catalog = new CoreFunctionCatalog();
		// Get a list of all created mocks
		Assertions.assertNotNull(catalog);
	}

}
