package com.github.eduardovalentim.easymath.processor.mathematical;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.eduardovalentim.easymath.annotations.EasyMath;
import com.github.eduardovalentim.easymath.processor.AbstractAnnotationProcessor;
import com.github.eduardovalentim.easymath.processor.mathematical.generators.GeneratorFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(MathematicalProcessor.class);

	private ReflectionUtils utils;

	/**
	 * Default constructor
	 */
	public MathematicalProcessor() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		this.utils = new ReflectionUtils(types, elements);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> types = new LinkedHashSet<>();
		types.add(EasyMath.class.getCanonicalName());
		return types;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		return generate(discover(annotations, roundEnv));
	}

	protected boolean generate(Map<TypeElement, List<ExecutableElement>> map) {
		LOGGER.trace("Entering...");
		/*
		 * Method protection
		 */
		requireNonNull(map);

		GeneratorFactory factory = new GeneratorFactory(filer);
		for (Entry<TypeElement, List<ExecutableElement>> entry : map.entrySet()) {
			VelocityContext context = new VelocityContext();
			context.put("utils", StringUtils.class);

			TypeElement interfaceElement = entry.getKey();

			Set<ConstantOperand> constants = new LinkedHashSet<>();
			Deque<FunctionModel> functions = new LinkedList<>();

			for (ExecutableElement methodElement : entry.getValue()) {
				FunctionModel model = new FunctionModelBuilder()
						.withInterfaceName(utils.getName(interfaceElement))
						.withMethodName(utils.getName(methodElement))
						.withType(utils.getMethodReturningType(methodElement))
						.withFormula(utils.getMethodFormula(methodElement))
						.withConstants(constants)
						.build();
				functions.add(model);
			}

			String moduleAndPkg = elements.getPackageOf(interfaceElement).getQualifiedName().toString();
			String relativeName = interfaceElement.getSimpleName().toString();
			
			context.put("package", moduleAndPkg);
			context.put("constants", constants);
			context.put("functions", functions);
			context.put("format", new FormatUtils());
			context.put("generator", getClass().getName());
			context.put("interfaceName", interfaceElement.getSimpleName());
			context.put("classname", relativeName + "Impl");

			EasyMath easyMath = interfaceElement.getAnnotation(EasyMath.class);
			GeneratorStrategy generator = factory.createGenerator(easyMath.generationMode());
			generator.generate(moduleAndPkg, relativeName, context, interfaceElement);
		}

		LOGGER.trace("Exiting...");
		return !map.isEmpty();
	}

	protected Map<TypeElement, List<ExecutableElement>> discover(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		LOGGER.trace("Entering...");
		/*
		 * Method protection
		 */
		requireNonNull(annotations, "Argument 'annotations' cannot be null.");
		requireNonNull(roundEnv, "Argument 'roundEnv' cannot be null.");
		/*
		 * Variable declaration
		 */
		Map<TypeElement, List<ExecutableElement>> mappedElements = new LinkedHashMap<>();
		/*
		 * Discovery process
		 */
		for (TypeElement annotation : annotations) {
			scanRoundEnvironment(roundEnv, mappedElements, annotation);
		}
		/*
		 * Return the result
		 */
		LOGGER.trace("Exiting...");
		return mappedElements;
	}

	/**
	 * 
	 * @param roundEnv
	 * @param mappedElements
	 * @param annotation
	 */
	private void scanRoundEnvironment(RoundEnvironment roundEnv, Map<TypeElement, List<ExecutableElement>> mappedElements,
			TypeElement annotation) {
		for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
			LOGGER.info("Processing annotation '{}' for element '{}' ...", annotation, element);

			// If the element not is an interface
			if (!utils.isInterface(element)) {
				throw new IllegalStateException("The annotated element must be an 'interface'.");
			}

			// If it is...
			readElementMethods((TypeElement) element, mappedElements);
		}
	}

	/**
	 * 
	 * @param interfaceElement
	 * @param mappedElements
	 */
	private void readElementMethods(TypeElement interfaceElement,
			Map<TypeElement, List<ExecutableElement>> mappedElements) {
		// Returns the fields, methods, constructors, record components,
		// and member classes and interfaces that are directly declared in
		// this class or interface.
		for (Element enclosedElement : interfaceElement.getEnclosedElements()) {
			if (enclosedElement instanceof ExecutableElement executable) {
				try {
					checkConvention(executable);
					mappedElements.computeIfAbsent(interfaceElement, k -> new LinkedList<>()).add(executable);
				} catch (IllegalStateException ex) {
					throw new IllegalStateException(format(
							"The method ''{0}.{1}'' don''t follow the convention: public [abstract] {? extends Number} {name}({? extends Number} {arg}, *);",
							interfaceElement, executable), ex);

				}
			}
		}
	}

	/**
	 * @param methodElement
	 * @param builder
	 * @return
	 */
	private void checkConvention(Element methodElement) {
		/*
		 * Method protection
		 */
		requireNonNull(methodElement, "Argument 'methodElement' cannot be null.");
		/*
		 * Check the number of parameters in the method
		 */
		if (utils.getMethodParametersCount(methodElement) <= 0) {
			throw new IllegalStateException("The method must have parameters!");
		}
		/*
		 * Check the result type
		 */
		if (!utils.isMethodReturningNumber(methodElement)) {
			throw new IllegalStateException("The method must declare a returnning type that extends java.lang.Number!");
		}
	}
}