package com.github.eduardovalentim.easymath.processor;

import java.util.Properties;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The abstract processor
 * 
 * @author eduardovalentim
 */
public abstract class AbstractAnnotationProcessor extends AbstractProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAnnotationProcessor.class);

	protected Messager messager;
	protected Types types;
	protected Elements elements;
	protected Filer filer;

	/**
	 * Public default constructor
	 */
	protected AbstractAnnotationProcessor() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SourceVersion getSupportedSourceVersion() {
		LOGGER.trace("Entering... ");
		SourceVersion sv = SourceVersion.latestSupported();

		LOGGER.trace("Exiting... ");
		return sv;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		LOGGER.trace("Entering...  ");

		super.init(processingEnv);

		Velocity.init(getVelocityProperties());

		types = processingEnv.getTypeUtils();
		elements = processingEnv.getElementUtils();
		messager = processingEnv.getMessager();
		filer = processingEnv.getFiler();

		LOGGER.trace("Exiting...  ");
	}

	private Properties getVelocityProperties() {
		LOGGER.trace("Entering...");

		Properties properties = new Properties();
		properties.setProperty("resource.loaders", "classpath");
		properties.setProperty("resource.loader.classpath.class", ClasspathResourceLoader.class.getName());

		LOGGER.trace("Exiting...");
		return properties;
	}

}
