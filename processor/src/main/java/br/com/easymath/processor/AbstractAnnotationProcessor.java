package br.com.easymath.processor;

import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAnnotationProcessor extends AbstractProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAnnotationProcessor.class);

	public static final String ENCODING = "UTF-8";
	
	protected Filer filer;
	protected Messager messager;
	protected Types typeUtils;
	protected Elements elementUtils;

	public AbstractAnnotationProcessor() {
		super();
	}
	
	@Override
	public SourceVersion getSupportedSourceVersion() {
		LOGGER.trace("Entering...");
		SourceVersion sv = SourceVersion.latestSupported();
		
		LOGGER.trace("Exiting...");
		return sv;
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		LOGGER.trace("Entering...");
		
		super.init(processingEnv);

		Velocity.init(getVelocityProperties());

		typeUtils = processingEnv.getTypeUtils();
	    elementUtils = processingEnv.getElementUtils();
		filer = processingEnv.getFiler();
		messager = processingEnv.getMessager();
		
		LOGGER.trace("Exiting...");
	}
	
	protected void generate(CharSequence name, Template template, VelocityContext context) {
		LOGGER.trace("Entering...");
		try {
			JavaFileObject jfo = filer.createSourceFile(name);
			try (Writer writer = jfo.openWriter()) {
				LOGGER.debug("Merging objects to template...");
				template.merge(context, writer);
				LOGGER.debug("Merging finished!");
			}
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
		LOGGER.trace("Exiting...");
	}
	
	private Properties getVelocityProperties() {
		LOGGER.trace("Entering...");
		
		Properties properties = new Properties();
		properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		properties.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		
		LOGGER.trace("Exiting...");
		return properties;
	}

}
