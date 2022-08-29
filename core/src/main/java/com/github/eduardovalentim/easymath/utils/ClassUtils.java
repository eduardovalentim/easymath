package com.github.eduardovalentim.easymath.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Utilities
 * 
 * @author eduardovalentim
 */
public class ClassUtils {

	static final Collator englishCollator = Collator.getInstance(Locale.ENGLISH);

	static final String keywords[] = { "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
			"class", "const", "continue", "default", "do", "double", "else", "extends", "false", "final", "finally",
			"float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
			"new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
			"super", "switch", "synchronized", "this", "throw", "throws", "transient", "true", "try", "void",
			"volatile", "while" };

	private static final Map<Class<?>, Class<?>> NUMBERS;

	static {
		HashMap<Class<?>, Class<?>> numbers = new HashMap<Class<?>, Class<?>>();
		numbers.put(short.class, Short.class);
		numbers.put(byte.class, Byte.class);
		numbers.put(int.class, Integer.class);
		numbers.put(long.class, Long.class);
		numbers.put(float.class, Float.class);
		numbers.put(double.class, Double.class);
		
		NUMBERS = Collections.unmodifiableMap(numbers);
	}
	
    /**
     * Private constructor to hide the implicit public one.
     */
	private ClassUtils() {
		super();
	}
	
	/**
	 * Check if the argument <code>s</code> is a valid java identifier
	 * 
	 * @param s
	 *            A possible identifier to validate
	 * @return True if <code>s</code> is valid
	 */
	public static boolean isValidJavaIdentifier(String s) {
		// an empty or null string cannot be a valid identifier
		if (s == null || s.length() == 0 || isJavaKeyword(s)) {
			return false;
		}

		char[] c = s.toCharArray();
		if (!Character.isJavaIdentifierStart(c[0])) {
			return false;
		}

		for (int i = 1; i < c.length; i++) {
			if (!Character.isJavaIdentifierPart(c[i])) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Numeric methods are methods that receive and return Numbers
	 * 
	 * @param klass
	 *            A class to be analyzed
	 * @return A list of methods
	 */
	public static List<Method> getNumericMethods(Class<?> klass) {
		if (klass == null) {
			throw new IllegalArgumentException("Argument 'klass' cannot be null.");
		}

		List<Method> methods = new LinkedList<>();

		for (Method method : klass.getMethods()) {
			boolean supported = false;
			// if the return is a number
			if (Number.class.isAssignableFrom(adapt(method.getReturnType()))) {

				// Check if all parameters are Numbers
				for (Parameter parameter : method.getParameters()) {
					supported = Number.class.isAssignableFrom(adapt(parameter.getType()));
					if (!supported) {
						break;
					}
				}
			}

			if (supported) {
				methods.add(method);
			}
		}

		return methods;
	}

	private static Class<?> adapt(Class<?> type) {
		Class<?> wrapper = NUMBERS.get(type);

		if (wrapper == null) {
			wrapper = type;
		}

		return wrapper;
	}

	private static boolean isJavaKeyword(String keyword) {
		return (Arrays.binarySearch(keywords, keyword, englishCollator) >= 0);
	}
}
