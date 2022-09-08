package com.github.eduardovalentim.easymath.utils;

/**
 * 
 * @author valentim
 *
 */
public class SystemUtils {

	public static final String LIBRARY_NAME = "easymath.library.name";
	
    /**
     * Private constructor to hide the implicit public one.
     */
	private SystemUtils() {
		super();
	}
	
	/**
	 * 
	 * @return
	 */
	public static final String getLibraryName() {
		String name = System.getProperty(LIBRARY_NAME);
		
		if (name == null || name.isEmpty() || name.isBlank()) {
			throw new IllegalStateException("You are calling this method without setting the system property: " + LIBRARY_NAME);
		}
		
		return name;
	}
}
