package org.bitbucket.easymath.processor;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public abstract class AbstractAnnotationProcessor extends AbstractProcessor {

	private static final Logger LOGGER = LogManager.getLogger(AbstractAnnotationProcessor.class);

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
		LOGGER.entry();
		return LOGGER.exit(SourceVersion.latestSupported());
	}

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		LOGGER.entry();
		
		super.init(processingEnv);

		Velocity.init(getVelocityProperties());

		typeUtils = processingEnv.getTypeUtils();
	    elementUtils = processingEnv.getElementUtils();
		filer = processingEnv.getFiler();
		messager = processingEnv.getMessager();
		
		LOGGER.exit();
	}
	
	protected void generate(CharSequence name, Template template, VelocityContext context) {
		try {
			JavaFileObject jfo = filer.createSourceFile(name);
			try (Writer writer = jfo.openWriter()) {
				LOGGER.info("Merging objects to template...");
				template.merge(context, writer);
				LOGGER.info("Merging finished!");
			}
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}

	}
	
	private Properties getVelocityProperties() {
		LOGGER.entry();
		
		Properties properties = new Properties();
		properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		properties.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		
		return LOGGER.exit(properties);
	}

}
