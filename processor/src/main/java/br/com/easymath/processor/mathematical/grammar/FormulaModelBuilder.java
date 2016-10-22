package br.com.easymath.processor.mathematical.grammar;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Deque;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.easymath.annotations.Formula;
import br.com.easymath.processor.mathematical.FunctionErrorListener;
import br.com.easymath.processor.mathematical.operation.Operation;
import br.com.easymath.processor.mathematical.operation.operand.ConstantOperand;
import br.com.easymath.processor.mathematical.operation.operand.InputOperand;

/**
 * @author eduardo.valentim
 */
public class FormulaModelBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormulaModelBuilder.class);
    
    private Formula formula;
    private String className;
    private String methodName;
    private String type;
    private Collection<ConstantOperand> constants;

	/**
	 * Public default constructor
	 */
	public FormulaModelBuilder() {
		super();
	}
	
	/**
	 * 
	 * @param methodName
	 * @return
	 */
	public FormulaModelBuilder withMethodName(String methodName) {
	    this.methodName = methodName;
	    return this;
	}
	
	/**
	 * 
	 * @param className
	 * @return
	 */
    public FormulaModelBuilder withClassName(String className) {
        this.className = className;
        return this;
    }

    /**
	 * 
	 * @param type
	 * @return
	 */
	public FormulaModelBuilder withType(String type) {
	    this.type = type;
	    return this;
	}
	
	/**
	 * 
	 * @param formula
	 * @return
	 */
	public FormulaModelBuilder withFormula(Formula formula) {
	    this.formula = formula;
	    return this;
	}

	/**
	 * 
	 * @param constants
	 * @return
	 */
    public FormulaModelBuilder withConstants(Collection<ConstantOperand> constants) {
        this.constants = constants;
        return this;
    }

    /**
     * 
     * @return
     */
    public FormulaModel build() {
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
        Set<InputOperand> inputs = visitor.getInputs();
        Deque<Operation> operations = visitor.getOperations();
        FormulaModel model = new FormulaModel();
        model.setType(type);
        model.setName(methodName);
        model.setFormula(formula);
        model.addAllConstants(constants);
        model.addAllInputs(inputs);
        model.addAllOperations(operations);

		return model;
	}
}
