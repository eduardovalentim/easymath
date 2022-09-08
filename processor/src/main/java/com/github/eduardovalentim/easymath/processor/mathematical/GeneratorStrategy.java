package com.github.eduardovalentim.easymath.processor.mathematical;

import javax.lang.model.element.Element;

import org.apache.velocity.VelocityContext;

public interface GeneratorStrategy {

	public static final String ENCODING = "UTF-8";

	/**
	 * 
	 * @param location
	 * @param moduleAndPkg
	 * @param relativeName
	 * @param template
	 * @param context
	 * @param originatingElements
	 */
	void generate(String moduleAndPkg, String relativeName, VelocityContext context,
            Element... originatingElements);

}
