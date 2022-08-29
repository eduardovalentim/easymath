package com.github.eduardovalentim.easymath.processor.mathematical;

import static java.lang.System.getProperty;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.eduardovalentim.easymath.annotations.Formula;
import com.github.eduardovalentim.easymath.processor.AbstractAnnotationProcessor;
import com.github.eduardovalentim.easymath.processor.mathematical.grammar.FunctionModel;
import com.github.eduardovalentim.easymath.processor.mathematical.grammar.FunctionModelBuilder;
import com.github.eduardovalentim.easymath.processor.mathematical.operation.operand.ConstantOperand;
import com.github.eduardovalentim.easymath.processor.mathematical.utils.FormatUtils;
import com.github.eduardovalentim.easymath.processor.mathematical.utils.ReflectionUtils;

/**
 * The processor
 * 
 * @author eduardo.valentim
 */
public class MathematicalProcessor extends AbstractAnnotationProcessor {

	private static final String LINE_SEPARATOR = getProperty("line.separator");
	private static final Logger LOGGER = LoggerFactory.getLogger(MathematicalProcessor.class);
	private static final String SUFFIX = "Impl";

	private Template template;
	private ReflectionUtils utils;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		this.template = Velocity.getTemplate("META-INF/templates/formulas.vm", ENCODING);
		this.utils = new ReflectionUtils(types, elements);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> supportedAnnotationTypes = new HashSet<>();
		supportedAnnotationTypes.add(Formula.class.getCanonicalName());
		return supportedAnnotationTypes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		LOGGER.trace("Entering... ");

		boolean processed = false;

		generate(discover(annotations, roundEnv));

		LOGGER.trace("Exiting... ");
		return processed;
	}

	protected void generate(Map<Element, List<ExecutableElement>> map) {
		LOGGER.trace("Entering...");
		/*
		 * Method protection
		 */
		requireNonNull(map);

		VelocityContext context = new VelocityContext();
		context.put("utils", StringUtils.class);

		for (Entry<Element, List<ExecutableElement>> entry : map.entrySet()) {
			Element element = entry.getKey();

			Set<ConstantOperand> constants = new HashSet<>();
			Deque<FunctionModel> functions = new LinkedList<>();

			for (ExecutableElement methodElement : entry.getValue()) {
				FunctionModel model = new FunctionModelBuilder().withInterfaceName(utils.getName(element))
						.withMethodName(utils.getName(methodElement))
						.withType(utils.getMethodReturningType(methodElement))
						.withFormula(utils.getMethodFormula(methodElement)).withConstants(constants).build();
				functions.add(model);
			}

			context.put("generator", getClass().getName());
			context.put("package", elements.getPackageOf(element).getQualifiedName());
			context.put("interfaceName", element.getSimpleName());
			context.put("classname", element.getSimpleName() + SUFFIX);
			context.put("constants", constants);
			context.put("functions", functions);
			context.put("format", FormatUtils.INSTANCE);

			generate(element.toString() + SUFFIX, template, context);
		}

		LOGGER.trace("Exiting...");
	}

	protected Map<Element, List<ExecutableElement>> discover(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		LOGGER.trace("Entering...");
		/*
		 * Method protection
		 */
		requireNonNull(annotations);
		requireNonNull(roundEnv);
		/*
		 * Variable declaration
		 */
		Map<Element, List<ExecutableElement>> map = new HashMap<>();
		/*
		 * Discovery process
		 */
		for (TypeElement te : annotations) {
			for (Element methodElement : roundEnv.getElementsAnnotatedWith(te)) {
				LOGGER.info("Processing annotation '{}' for element '{}' ...", te, methodElement);
				/*
				 * Check convention
				 */
				StringBuilder builder = new StringBuilder();
				if (isConventionFollowed(methodElement, builder)) {
					Element classElement = methodElement.getEnclosingElement();
					addMethod(map, methodElement, classElement);
				} else {
					throw new IllegalStateException(builder.toString());
				}
			}
		}
		/*
		 * Return the result
		 */
		LOGGER.trace("Exiting...");
		return map;
	}

	/**
	 * @param methodElement
	 * @param builder 
	 * @return
	 */
	private boolean isConventionFollowed(Element methodElement, StringBuilder builder) {
		/*
		 * Method protection
		 */
		requireNonNull(methodElement);
		/*
		 * Default result
		 */
		boolean result = true;
		/*
		 * Get class of the method
		 */
		Element element = methodElement.getEnclosingElement();
		/*
		 * Check the abstract modifier in the class
		 */
		if (!utils.isInterface(element)) {
			result = false;
			builder.append(format("    * The enclosing type ''{0}'' must be an interface!", element));
			builder.append(LINE_SEPARATOR);
		}
		/*
		 * Check the varargs in the method
		 */
		if (!utils.isVarArgsMethod(methodElement)) {
			result = false;
			builder.append("    * The method must have a VarArgs argument!");
			builder.append(LINE_SEPARATOR);
		}
		/*
		 * Check the number of parameters in the method
		 */
		if (utils.getMethodParametersCount(methodElement) != 1) {
			result = false;
			builder.append("    * The method must have only one argument!");
			builder.append(LINE_SEPARATOR);
		}
		/*
		 * Check the result type
		 */
		if (!utils.isMethodReturningNumber(methodElement)) {
			result = false;
			builder.append("    * The method must declare a returnning type that extends java.lang.Number!");
			builder.append(LINE_SEPARATOR);
		}
		/*
		 * If has content
		 */
		if (builder.length() > 0) {
			builder.insert(0, LINE_SEPARATOR);
			builder.insert(0,
					format("The method ''{0}'' don''t follow the convention: public abstract {? extends Number} {name}(Number...args);",
							methodElement));
			LOGGER.warn("{}", builder);
		}
		/*
		 * Result
		 */
		return result;
	}

	/**
	 * @param map
	 * @param methodElement
	 * @param classElement
	 */
	private void addMethod(Map<Element, List<ExecutableElement>> map, Element methodElement, Element classElement) {
		/*
		 * Get the formulas
		 */
		map.computeIfAbsent(classElement, k -> new LinkedList<>()).add((ExecutableElement) methodElement);
	}
}