package org.bitbucket.easymath.processor.mathematical.function;

import org.antlr.v4.runtime.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaBaseVisitor;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.BinaryContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.ConstantContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.ExpressionContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.InputContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.UnaryContext;

public class StackVisitor extends FormulaBaseVisitor<Void> {

	private static final Logger LOGGER = LogManager.getLogger(StackVisitor.class);

	private static final int RIGHT = 1;
	
	private static final int LEFT = 0;

	
	
	public StackVisitor() {
		super();
	}

	@Override
	public Void visitInput(InputContext ctx) {
		LOGGER.debug(ctx.getText());
		return null;
	}

	@Override
	public Void visitConstant(ConstantContext ctx) {
		LOGGER.debug(ctx.getText());
		return null;
	}

	@Override
	public Void visitBinary(BinaryContext ctx) {
		ExpressionContext left = ctx.expression(LEFT);
		Token operator = ctx.operator;
		ExpressionContext right = ctx.expression(RIGHT);

		LOGGER.debug("left: {}, op: {}, right: {}", left.getText(), operator.getText(), right.getText());

		visit(left);
		visit(right);

		return null;
	}

	@Override
	public Void visitUnary(UnaryContext ctx) {
		ExpressionContext expr = ctx.expression();
		Token operator = ctx.operator;
		LOGGER.debug("expr: {}, op: {}", expr.getText(), operator.getText());

		visit(expr);

		return null;
	}

}
