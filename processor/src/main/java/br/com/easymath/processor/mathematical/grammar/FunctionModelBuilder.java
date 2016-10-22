package br.com.easymath.processor.mathematical.grammar;

import static java.util.Objects.requireNonNull;

import java.util.Collection;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.easymath.annotations.Formula;
import br.com.easymath.processor.mathematical.FunctionErrorListener;
import br.com.easymath.processor.mathematical.operation.operand.ConstantOperand;

/**
 * @author eduardo.valentim
 */
public class FunctionModelBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionModelBuilder.class);
    
    private Formula formula;
    private String className;
    private String methodName;
    private String type;
    private Collection<ConstantOperand> constants;

	/**
	 * Public default constructor
	 */
	public FunctionModelBuilder() {
		super();
	}
	
	/**
	 * 
	 * @param methodName
	 * @return
	 */
	public FunctionModelBuilder withMethodName(String methodName) {
	    this.methodName = methodName;
	    return this;
	}
	
	/**
	 * 
	 * @param className
	 * @return
	 */
    public FunctionModelBuilder withClassName(String className) {
        this.className = className;
        return this;
    }

    /**
	 * 
	 * @param type
	 * @return
	 */
	public FunctionModelBuilder withType(String type) {
	    this.type = type;
	    return this;
	}
	
	/**
	 * 
	 * @param formula
	 * @return
	 */
	public FunctionModelBuilder withFormula(Formula formula) {
	    this.formula = formula;
	    return this;
	}

	/**
	 * 
	 * @param constants
	 * @return
	 */
    public FunctionModelBuilder withConstants(Collection<ConstantOperand> constants) {
        this.constants = constants;
        return this;
    }

    /**
     * 
     * @return
     */
    public FunctionModel build() {
        requireNonNull(methodName);
        requireNonNull(type);
        requireNonNull(formula);
        requireNonNull(constants);
        
        GrammarTreeVisitor visitor = new GrammarTreeVisitor(type);
        /*
         * create a CharStream that reads from standard input
         */
        ANTLRInputStream input = new ANTLRInputStream(formula.value());
        /*
         * create a lexer that feeds off of input CharStream
         */
        FormulaLexer lexer = new FormulaLexer(input);
        /*
         * create a buffer of tokens pulled from the lexer
         */
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        /*
         * create a parser that feeds off the tokens buffer
         */
        FormulaParser parser = new FormulaParser(tokens);
        /*
         * add a custom error report for syntax
         */
        parser.removeErrorListeners();
        parser.addErrorListener(new FunctionErrorListener(className, methodName, formula.value()));
        try {
            /*
             * begin parsing at formula rule
             */
            ParseTree tree = parser.formula();
            /*
             * Visit the tree
             */
            visitor.visit(tree);
        } catch (RecognitionException ex) {
            LOGGER.error("Recognition exception is never thrown, only declared.", ex);
            throw new IllegalStateException("Recognition exception is never thrown, only declared.", ex);
        }

        constants.addAll(visitor.getConstants());
        
        FunctionModel model = new FunctionModel();
        model.setType(type);
        model.setName(methodName);
        model.setFormula(formula);
        model.addAllConstants(constants);
        model.addAllInputs(visitor.getInputs());
        model.addAllOperations(visitor.getOperations());

		return model;
	}
}
