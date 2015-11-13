package org.bitbucket.easymath.processor.mathematical;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.Mathematical;
import org.bitbucket.easymath.processor.AbstractAnnotationProcessor;
import org.bitbucket.easymath.processor.mathematical.function.FunctionContext;
import org.bitbucket.easymath.processor.mathematical.function.FunctionContextBuilder;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaLexer;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser;

@SupportedAnnotationTypes({ "org.bitbucket.easymath.annotations.Mathematical" })
public class MathematicalProcessor extends AbstractAnnotationProcessor {

    private static final Logger LOGGER = LogManager.getLogger(MathematicalProcessor.class);
    private static final String SUFFIX = "Formulas";

    private Template template;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        template = Velocity.getTemplate("META-INF/templates/formulas.vm", ENCODING);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        LOGGER.entry();

        boolean processed = false;
        VelocityContext context = new VelocityContext();
        context.put("utils", new StringUtils());

        for (TypeElement te : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(te)) {
                LOGGER.info("Processing annotation '{}' for element '{}' ...", te, e);

                if (!ElementKind.CLASS.equals(e.getKind())) {
                    LOGGER.warn("Element '{}' is not a class. Ignoring...", e);

                    continue;
                }

                Mathematical annotatedMathematical = e.getAnnotation(Mathematical.class);
                Function[] annotatedFunctions = annotatedMathematical.functions();

                List<FunctionContext> functions = new LinkedList<>();
                for (Function function : annotatedFunctions) {
                    functions.add(compile(function));
                }

                context.put("generator", getClass().getName());
                context.put("package", elementUtils.getPackageOf(e).getQualifiedName());
                context.put("classname", e.getSimpleName() + SUFFIX);
                context.put("functions", functions);

                generate(e.toString() + SUFFIX, template, context);
            }
        }

        return LOGGER.exit(processed);
    }

    public FunctionContext compile(Function function) {
        LOGGER.entry();

        FunctionContextBuilder builder = new FunctionContextBuilder(function.name(), function.formula(), function.use());
        try {
            LOGGER.info("Compiling formula: {}", function.formula());
            // create a CharStream that reads from standard input
            ANTLRInputStream input = new ANTLRInputStream(function.formula());
            // create a lexer that feeds off of input CharStream
            FormulaLexer lexer = new FormulaLexer(input);
            // create a buffer of tokens pulled from the lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // create a parser that feeds off the tokens buffer
            FormulaParser parser = new FormulaParser(tokens);
            // begin parsing at formula rule
            ParseTree tree = parser.formula();
            // Create a generic parse tree walker that can trigger callbacks
            ParseTreeWalker walker = new ParseTreeWalker();
            // Walk the tree created during the parse, trigger callbacks
            walker.walk(builder, tree);
        } catch (RecognitionException e) {
            throw new IllegalStateException("Recognition exception is never thrown, only declared.");
        }

        return LOGGER.exit(builder.build());
    }
}