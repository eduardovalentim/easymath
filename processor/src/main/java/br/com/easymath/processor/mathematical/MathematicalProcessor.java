package br.com.easymath.processor.mathematical;

import static br.com.easymath.processor.mathematical.utils.ReflectionUtils.isAbstractClass;
import static java.util.Objects.requireNonNull;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.easymath.annotations.Formula;
import br.com.easymath.processor.AbstractAnnotationProcessor;
import br.com.easymath.processor.mathematical.grammar.FunctionModel;
import br.com.easymath.processor.mathematical.grammar.FunctionModelBuilder;
import br.com.easymath.processor.mathematical.operation.operand.ConstantOperand;
import br.com.easymath.processor.mathematical.utils.ReflectionUtils;

public class MathematicalProcessor extends AbstractAnnotationProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MathematicalProcessor.class);
    private static final String SUFFIX = "Math";

    private Template template;

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        template = Velocity.getTemplate("META-INF/templates/formulas.vm", ENCODING);
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
		LOGGER.trace("Entering...");

        boolean processed = false;

        generate(discover(annotations, roundEnv));
        
		LOGGER.trace("Exiting...");
        return processed;
    }

	protected void generate(Map<Element, List<Formula>> map) {
        VelocityContext context = new VelocityContext();
        context.put("utils", new StringUtils());

        for (Entry<Element, List<Formula>> entry : map.entrySet()) {
			Element element = entry.getKey();
			
			Set<ConstantOperand> constants = new LinkedHashSet<>();
			Deque<FunctionModel> functions = new LinkedList<>();
	        for (Formula function : entry.getValue()) {
	        	FunctionModel model = new FunctionModelBuilder()
	        		.withClassname(element.toString())
	        		.withFunction(function)
	        		.withConstants(constants)
	        		.build();
	            functions.add(model);
	        }
			
	        context.put("generator", getClass().getName());
	        context.put("package", elementUtils.getPackageOf(element).getQualifiedName());
	        context.put("classname", element.getSimpleName() + SUFFIX);
	        context.put("constants", constants);
	        context.put("functions", functions);

	        generate(element.toString() + SUFFIX, template, context);
		}
	}

	protected Map<Element, List<Formula>> discover(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		/*
		 * Method protection
		 */
		requireNonNull(annotations);
		requireNonNull(roundEnv);
		/*
		 * Variable declaration
		 */
		Map<Element, List<Formula>> map = new HashMap<>();
		/*
		 * Discovery process
		 */
        for (TypeElement te : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(te)) {
                LOGGER.info("Processing annotation '{}' for element '{}' ...", te, element);
                /*
                 * Get the enclosed abstract class
                 */
                Element candidate = element.getEnclosingElement();
                /*
                 * Check
                 */
                if (!isAbstractClass(candidate)) {
                	LOGGER.warn("Processing annotation '{}' for element '{}' ...", te, element);
                }
                /*
                 * Get the functions
                 */
                List<Formula> functions = map.get(candidate);
                /*
                 * If absent
                 */
                if (functions == null) {
                	/*
                	 * Create a new list
                	 */
                	functions = new LinkedList<>();
                	/*
                	 * Store it in the map
                	 */
                	map.put(candidate, functions);
                }
                /*
                 * Get the function annotation
                 */
                Formula function = element.getAnnotation(Formula.class);
                /*
                 * Add it
                 */
                functions.add(function);
            }
        }
        /*
         * Return the result
         */
        return map;
	}
}