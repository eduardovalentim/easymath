package org.bitbucket.easymath.processor.mathematical.function;

import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bitbucket.easymath.annotations.NumberType;
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
import org.bitbucket.easymath.processor.mathematical.operation.operand.ConstantOperand;
import org.bitbucket.easymath.processor.mathematical.operation.operand.InputOperand;
import org.bitbucket.easymath.processor.mathematical.operation.operand.Operand;
import org.bitbucket.easymath.processor.mathematical.operation.operand.ResultOperand;

public class FunctionModelVisitor extends FormulaBaseVisitor<String> {

    private static final Logger LOGGER = LogManager.getLogger(FunctionModelVisitor.class);

    private static final int RIGHT = 1;

    private static final int LEFT = 0;

    private Deque<Operation> operations;
    private Set<String> constants;
    private Set<String> inputs;
    private NumberType type;
    private int id = 0;

    public FunctionModelVisitor(NumberType type) {
        super();
        this.type = type;
        this.operations = new LinkedList<>();
        this.inputs = new LinkedHashSet<>();
        this.constants = new LinkedHashSet<>();
    }

    @Override
    public String visitInput(InputContext ctx) {
        String text = ctx.getText();
        LOGGER.trace(text);

        inputs.add(text);
        return text;
    }

    @Override
    public String visitConstant(ConstantContext ctx) {
        String text = ctx.getText();
        LOGGER.trace(text);

        constants.add(text);
        return text;
    }

    @Override
    public String visitBinary(BinaryContext ctx) {
        ExpressionContext left = ctx.expression(LEFT);
        String operator = ctx.operator.getText();
        ExpressionContext right = ctx.expression(RIGHT);

        Operand leftOperand = createOperand(visit(left));
        Operand rightOperand = createOperand(visit(right));

        BinaryOperation operation = new BinaryOperation("$r" + id++, type, leftOperand, left.getText(), operator,
                rightOperand, right.getText());
        operations.add(operation);

        LOGGER.debug("{} = {} {} {} (left={}, operator={} right={})", operation, leftOperand, operator, rightOperand,
                left.getText(), operator, right.getText());

        return operation.toString();
    }

    @Override
    public String visitUnary(UnaryContext ctx) {
        ExpressionContext expr = ctx.expression();
        String operator = ctx.operator.getText();

        Operand operand = createOperand(visit(expr));

        UnaryOperation operation = new UnaryOperation("$r" + id++, type, operand, operator, expr.getText());
        operations.add(operation);
        LOGGER.debug("{} = {} {} (operator={}, operand={})", operation, operator, operand, operator,
                expr.getText());

        return operation.toString();
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

    @Override
    public String visitErrorNode(ErrorNode node) {
        throw new IllegalArgumentException();
    }
    
    public Deque<Operation> getOperations() {
        return operations;
    }

    public Set<InputOperand> getInputs() {
        Set<InputOperand> operands = new LinkedHashSet<>();
        for (String input : inputs) {
            operands.add(new InputOperand(type, input));
        }

        return operands;
    }

    public Set<ConstantOperand> getConstants() {
        Set<ConstantOperand> operands = new LinkedHashSet<>();
        for (String constant : constants) {
            operands.add(new ConstantOperand(type, constant));
        }

        return operands;
    }

    private Operand createOperand(String value) {
        Operand operand = null;

        if (constants.contains(value)) {
            operand = new ConstantOperand(type, value);
        } else if (inputs.contains(value)) {
            operand = new InputOperand(type, value);
        } else {
            operand = new ResultOperand(type, value);
        }

        return operand;
    }
}
