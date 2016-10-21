package br.com.easymath.processor.mathematical.grammar;

import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import br.com.easymath.processor.mathematical.grammar.FormulaBaseVisitor;
import br.com.easymath.processor.mathematical.grammar.FormulaParser.BinaryContext;
import br.com.easymath.processor.mathematical.grammar.FormulaParser.BracesContext;
import br.com.easymath.processor.mathematical.grammar.FormulaParser.BracketsContext;
import br.com.easymath.processor.mathematical.grammar.FormulaParser.ConstantContext;
import br.com.easymath.processor.mathematical.grammar.FormulaParser.ExpressionContext;
import br.com.easymath.processor.mathematical.grammar.FormulaParser.FormulaContext;
import br.com.easymath.processor.mathematical.grammar.FormulaParser.FunctionContext;
import br.com.easymath.processor.mathematical.grammar.FormulaParser.InputContext;
import br.com.easymath.processor.mathematical.grammar.FormulaParser.ParenthesisContext;
import br.com.easymath.processor.mathematical.grammar.FormulaParser.UnaryContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.easymath.annotations.NumberType;
import br.com.easymath.processor.mathematical.operation.BinaryOperation;
import br.com.easymath.processor.mathematical.operation.FunctionOperation;
import br.com.easymath.processor.mathematical.operation.Operation;
import br.com.easymath.processor.mathematical.operation.UnaryOperation;
import br.com.easymath.processor.mathematical.operation.operand.ConstantOperand;
import br.com.easymath.processor.mathematical.operation.operand.InputOperand;
import br.com.easymath.processor.mathematical.operation.operand.Operand;
import br.com.easymath.processor.mathematical.operation.operand.ResultOperand;

public class GrammarTreeVisitor extends FormulaBaseVisitor<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrammarTreeVisitor.class);

    private static final int RIGHT = 1;

    private static final int LEFT = 0;

    private Deque<Operation> operations;
    private Set<String> constants;
    private Set<String> inputs;
    private NumberType type;
    private int id = 0;

    private String formula;

    public GrammarTreeVisitor(NumberType type) {
        super();
        this.type = type;
        this.operations = new LinkedList<>();
        this.inputs = new LinkedHashSet<>();
        this.constants = new LinkedHashSet<>();
    }

    /*
     * GETTERS
     */

    public String getFormula() {
        return formula;
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

    /*
     * VISIT METHODS
     */

    @Override
    public String visitFormula(FormulaContext ctx) {
        this.formula = ctx.getText();
        LOGGER.debug("Compiling formula: {}", formula);

        return visit(ctx.expression());
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
    public String visitFunction(FunctionContext ctx) {
        // Generate the operation id
        String generatedId = generateId(ctx);

        String name = ctx.Identifier().getText();

        Deque<Operand> operands = new LinkedList<>();
        for (ExpressionContext expr : ctx.expression()) {
            operands.add(createOperand(visit(expr)));
        }

        FunctionOperation operation = new FunctionOperation(generatedId, name, type, operands, ctx.getText());
        operations.add(operation);

        LOGGER.debug("formula={}, id={}, fragment={}", formula, generatedId, ctx.getText());

        return generatedId;
    }

    @Override
    public String visitBinary(BinaryContext ctx) {
        String generatedId = generateId(ctx);

        ExpressionContext left = ctx.expression(LEFT);
        String operator = ctx.operator.getText();
        ExpressionContext right = ctx.expression(RIGHT);

        Operand leftOperand = createOperand(visit(left));
        Operand rightOperand = createOperand(visit(right));

        BinaryOperation operation = new BinaryOperation(generatedId, type, leftOperand, operator, rightOperand, ctx.getText());
        operations.add(operation);

        LOGGER.debug("formula={}, id={}, fragment={}", formula, generatedId, ctx.getText());

        return generatedId;
    }

    @Override
    public String visitUnary(UnaryContext ctx) {
        String generatedId = generateId(ctx);

        ExpressionContext expr = ctx.expression();
        String operator = ctx.operator.getText();

        Operand operand = createOperand(visit(expr));

        UnaryOperation operation = new UnaryOperation(generatedId, type, operand, operator, ctx.getText());
        operations.add(operation);
        LOGGER.debug("formula={}, id={}, fragment={}", formula, generatedId, ctx.getText());

        return generatedId;
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

    /*
     * PRIVATE METHODS
     */

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

    private String generateId(ExpressionContext ctx) {
        return String.format("r%d", id++);
    }

}
