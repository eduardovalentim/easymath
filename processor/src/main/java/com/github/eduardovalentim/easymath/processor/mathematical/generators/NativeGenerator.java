package com.github.eduardovalentim.easymath.processor.mathematical.generators;

import static java.util.Objects.requireNonNull;
import static javax.tools.StandardLocation.SOURCE_OUTPUT;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.eduardovalentim.easymath.processor.mathematical.GeneratorStrategy;

public class NativeGenerator implements GeneratorStrategy {

	private static final Logger LOGGER = LoggerFactory.getLogger(NativeGenerator.class);

	private Filer filer;

	private Template headerTemplate;
	private Template nativeTemplate;
	private Template libraryTemplate;

	public NativeGenerator(Filer filer) {
		this.filer = requireNonNull(filer, "Argument 'filer' cannot be null.");
		this.headerTemplate = Velocity.getTemplate("META-INF/templates/header.vm", ENCODING);
		this.nativeTemplate = Velocity.getTemplate("META-INF/templates/native.vm", ENCODING);
		this.libraryTemplate = Velocity.getTemplate("META-INF/templates/library.vm", ENCODING);
	}

	@Override
	public void generate(String moduleAndPkg, String relativeName, VelocityContext context,
            Element... originatingElements) {
		generateJavaSource(moduleAndPkg, relativeName, context, originatingElements);
		generateResource(headerTemplate, moduleAndPkg, relativeName + ".h", context, originatingElements);
		generateResource(nativeTemplate, moduleAndPkg, relativeName + ".c", context, originatingElements);
	}

	private void generateJavaSource(String moduleAndPkg, String relativeName, VelocityContext context, Element... originatingElements) {
		LOGGER.trace("Entering...");

		requireNonNull(moduleAndPkg, "Argument 'moduleAndPkg' cannot be null.");
		requireNonNull(relativeName, "Argument 'relativeName' cannot be null.");
		requireNonNull(context, "Argument 'context' cannot be null.");

		try {
			String name = moduleAndPkg + "." + relativeName + "Library";
			JavaFileObject jfo = filer.createSourceFile(name, originatingElements);
			try (Writer writer = jfo.openWriter()) {
				LOGGER.debug("Merging objects to template...");
				libraryTemplate.merge(context, writer);
				LOGGER.debug("Merging finished!");
			}
		} catch (IOException ex) {
			throw new IllegalStateException(ex);
		}
		LOGGER.trace("Exiting...");
	}

	private void generateResource(Template template, String moduleAndPkg, String relativeName, VelocityContext context, Element... originatingElements) {
		LOGGER.trace("Entering...");

		requireNonNull(template, "Argument 'template' cannot be null.");
		requireNonNull(moduleAndPkg, "Argument 'moduleAndPkg' cannot be null.");
		requireNonNull(relativeName, "Argument 'relativeName' cannot be null.");
		requireNonNull(context, "Argument 'context' cannot be null.");

		try {
			FileObject fo = filer.createResource(SOURCE_OUTPUT, moduleAndPkg, relativeName, originatingElements);
			try (Writer writer = fo.openWriter()) {
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
