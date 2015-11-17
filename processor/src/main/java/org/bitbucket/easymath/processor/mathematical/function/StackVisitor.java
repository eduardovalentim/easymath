package org.bitbucket.easymath.processor.mathematical.function;

import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaBaseVisitor;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.BinaryContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.BracesContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.BracketsContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.ConstantContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.ExpressionContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.InputContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.ParenthesisContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.UnaryContext;
import org.bitbucket.easymath.processor.mathematical.operation.BinaryOperation;
import org.bitbucket.easymath.processor.mathematical.operation.Operation;
import org.bitbucket.easymath.processor.mathematical.operation.UnaryOperation;

public class StackVisitor extends FormulaBaseVisitor<String> {

	private static final Logger LOGGER = LogManager.getLogger(StackVisitor.class);

	private static final int RIGHT = 1;
	
	private static final int LEFT = 0;
	
	private Stack<Operation> stack;
	
	private int id = 0;
	
	public StackVisitor() {
		super();
		this.stack = new Stack<>();
	}

	@Override
	public String visitInput(InputContext ctx) {
		LOGGER.trace(ctx.getText());
		return ctx.getText();
	}

	@Override
	public String visitConstant(ConstantContext ctx) {
		LOGGER.trace(ctx.getText());
		return ctx.getText();
	}

	@Override
	public String visitBinary(BinaryContext ctx) {
		ExpressionContext left = ctx.expression(LEFT);
		String operator = ctx.operator.getText();
		ExpressionContext right = ctx.expression(RIGHT);

		LOGGER.trace("left: {}, op: {}, right: {}", left.getText(), operator, right.getText());

		String leftOperand = visit(left);
		String rightOperand = visit(right);
		
		stack.push(new BinaryOperation(id++, leftOperand, operator, rightOperand));
        LOGGER.debug("{} = {} {} {}", UnaryOperation.declareId(id), leftOperand, operator, rightOperand);

		return BinaryOperation.declareId(id);
	}

	@Override
	public String visitUnary(UnaryContext ctx) {
		ExpressionContext expr = ctx.expression();
		String operator = ctx.operator.getText();
		
		LOGGER.trace("expr: {}, op: {}", expr.getText(), operator);

		String operand = visit(expr);

		stack.push(new UnaryOperation(id++, operator, operand));
        LOGGER.debug("{} = {} {}", UnaryOperation.declareId(id), operator, operand);
		
		return UnaryOperation.declareId(id);
	}

	@Override
	public String visitParenthesis(ParenthesisContext ctx) {
	    return visit(ctx.expression());
	}
	
	@Override
	public String visitBraces(BracesContext ctx) {
	    return visit(ctx.expression());
	}
	
	@Override
	public String visitBrackets(BracketsContext ctx) {
	    return visit(ctx.expression());
	}
}
