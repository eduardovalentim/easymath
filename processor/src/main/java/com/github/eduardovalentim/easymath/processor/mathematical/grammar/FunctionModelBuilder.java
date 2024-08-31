package com.github.eduardovalentim.easymath.processor.mathematical.grammar;

import static java.util.Objects.requireNonNull;

import java.util.Collection;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import com.github.eduardovalentim.easymath.annotations.Formula;
import com.github.eduardovalentim.easymath.processor.mathematical.FunctionErrorListener;
import com.github.eduardovalentim.easymath.processor.mathematical.operation.operand.ConstantOperand;

import br.com.easymath.processor.mathematical.grammar.FormulaLexer;
import br.com.easymath.processor.mathematical.grammar.FormulaParser;

/**
 * @author eduardo.valentim
 */
public class FunctionModelBuilder {

    private Formula formula;
    private String interfaceName;
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
	 * The method name
	 * 
	 * @param methodName The method name
	 * @return A reference to this builder
	 */
	public FunctionModelBuilder withMethodName(String methodName) {
	    this.methodName = methodName;
	    return this;
	}

	/**
	 * The classname for this builder
	 * 
	 * @param interfaceName
	 * @return
	 */
    public FunctionModelBuilder withInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
        return this;
    }

    /**
	 * The type for this builder
	 * 
	 * @param type The type
	 * @return A reference to this builder
	 */
	public FunctionModelBuilder withType(String type) {
	    this.type = type;
	    return this;
	}
	
	/**
	 * The formula for this builder
	 * 
	 * @param formula The formula
	 * @return A reference to this builder
	 */
	public FunctionModelBuilder withFormula(Formula formula) {
	    this.formula = formula;
	    return this;
	}

	/**
	 * The constants for this builder
	 * 
	 * @param constants The constants
	 * @return A reference to this builder
	 */
    public FunctionModelBuilder withConstants(Collection<ConstantOperand> constants) {
        this.constants = constants;
        return this;
    }

    /**
     * Create a new FunctionModel
	 * @return A new FunctionModel
     */
    public FunctionModel build() {
        requireNonNull(methodName, "methodName cannot be null");
        requireNonNull(type, "type cannot be null");
        requireNonNull(formula, "formula cannot be null");
        requireNonNull(constants, "constants cannot be null");
        
        GrammarTreeVisitor visitor = new GrammarTreeVisitor(type);
        /*
         * create a CharStream that reads from standard input
         */
        CodePointCharStream input = CharStreams.fromString(formula.value());
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
        parser.addErrorListener(new FunctionErrorListener(interfaceName, methodName, formula.value()));
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
