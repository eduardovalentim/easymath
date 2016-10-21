package br.com.easymath.processor.mathematical.grammar;

import java.util.Collection;
import java.util.Deque;
import java.util.Set;

import javax.lang.model.element.Element;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import br.com.easymath.annotations.Formula;
import br.com.easymath.processor.mathematical.FunctionErrorListener;
import br.com.easymath.processor.mathematical.operation.Operation;
import br.com.easymath.processor.mathematical.operation.operand.ConstantOperand;
import br.com.easymath.processor.mathematical.operation.operand.InputOperand;

public class FunctionModelBuilder {

	private Element element;
	private String classname;
	private Formula function;
	private Collection<ConstantOperand> constants;
	
	public FunctionModelBuilder() {
		super();
	}
	
	public FunctionModelBuilder withElement(Element element) {
		this.element = element;
		return this;
	}
	
	public FunctionModelBuilder withClassname(String classname) {
		this.classname = classname;
		return this;
	}

	public FunctionModelBuilder withFunction(Formula function) {
		this.function = function;
		return this;
	}

	public FunctionModelBuilder withConstants(Collection<ConstantOperand> constants) {
		this.constants = constants;
		return this;
	}
	
	public FunctionModel build() {
        GrammarTreeVisitor visitor = new GrammarTreeVisitor(function.using());
        /*
         * create a CharStream that reads from standard input
         */
        ANTLRInputStream input = new ANTLRInputStream(function.value());
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
        parser.addErrorListener(new FunctionErrorListener(classname, function.name(), function.value()));
        try {
            /*
             * begin parsing at formula rule
             */
            ParseTree tree = parser.formula();
            /*
             * Visit the tree
             */
            visitor.visit(tree);
        } catch (RecognitionException e) {
            throw new IllegalStateException("Recognition exception is never thrown, only declared.");
        }

        constants.addAll(visitor.getConstants());
        Set<InputOperand> inputs = visitor.getInputs();
        Deque<Operation> operations = visitor.getOperations();
        FunctionModel model = new FunctionModel(function, visitor.getFormula(), inputs, visitor.getConstants(), operations);

		return model;
	}
}
