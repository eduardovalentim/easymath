package org.bitbucket.easymath.processor.mathematical.function;

import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bitbucket.easymath.annotations.Function.Type;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaBaseListener;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.BinaryContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.ConstantContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.InputContext;
import org.bitbucket.easymath.processor.mathematical.grammar.FormulaParser.UnaryContext;

public class FunctionContextBuilder extends FormulaBaseListener {

    private static final Logger LOGGER = LogManager.getLogger(FunctionContextBuilder.class);

    private FunctionContext context;

    public FunctionContextBuilder(String name, String expression, Type aType) {
        String type = aType == Type.BIGDECIMAL ? "BigDecimal" : "double";
        context = new FunctionContext(name, expression, type);
    }

    public FunctionContext build() {
        Stack<String> terms = context.getTerms();
        while (terms.size() > 0) {
            String term = terms.pop();
            LOGGER.info(term);
        }

        return context;
    }

    @Override
    public void enterUnary(UnaryContext ctx) {
        ParseTree child = ctx.getChild(0);
        if (child instanceof InputContext || child instanceof ConstantContext) {
            context.pushTerm(child.getText());
        }
        context.pushTerm(ctx.operator.getText());
    }

    @Override
    public void enterBinary(BinaryContext ctx) {
        ParseTree child = ctx.getChild(2);
        if (child instanceof InputContext || child instanceof ConstantContext) {
            context.pushTerm(child.getText());
        }
        child = ctx.getChild(0);
        if (child instanceof InputContext || child instanceof ConstantContext) {
            context.pushTerm(child.getText());
        }
        context.pushTerm(ctx.operator.getText());
    }

    @Override
    public void enterConstant(ConstantContext ctx) {
        context.addConstant(ctx.getChild(0).getText());
    }

    @Override
    public void enterInput(InputContext ctx) {
        context.addInput(ctx.getChild(0).getText());
    }
}
