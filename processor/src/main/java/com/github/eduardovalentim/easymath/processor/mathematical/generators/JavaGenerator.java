package com.github.eduardovalentim.easymath.processor.mathematical.generators;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.JavaFileObject;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.eduardovalentim.easymath.processor.mathematical.GeneratorStrategy;

public class JavaGenerator implements GeneratorStrategy {

	private static final Logger LOGGER = LoggerFactory.getLogger(JavaGenerator.class);

	private Filer filer;

	private Template template;

	/**
	 * Constructor
	 * 
	 * @param filer
	 */
	public JavaGenerator(Filer filer) {
		this.filer = requireNonNull(filer, "Argument 'filer' cannot be null.");
		this.template = Velocity.getTemplate("META-INF/templates/java.vm", ENCODING);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generate(String moduleAndPkg, String relativeName, VelocityContext context,
            Element... originatingElements) {
		LOGGER.trace("Entering...");

		requireNonNull(moduleAndPkg, "Argument 'moduleAndPkg' cannot be null.");
		requireNonNull(relativeName, "Argument 'relativeName' cannot be null.");
		requireNonNull(context, "Argument 'context' cannot be null.");

		try {
			String className = String.valueOf(context.get("classname"));
			String name = moduleAndPkg + "." + className;
			JavaFileObject jfo = filer.createSourceFile(name, originatingElements);
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

}
