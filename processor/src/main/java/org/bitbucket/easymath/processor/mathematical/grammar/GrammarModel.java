package org.bitbucket.easymath.processor.mathematical.grammar;

import java.math.RoundingMode;
import java.util.Deque;
import java.util.Set;

import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.NumberType;
import org.bitbucket.easymath.processor.mathematical.operation.Operation;
import org.bitbucket.easymath.processor.mathematical.operation.operand.ConstantOperand;
import org.bitbucket.easymath.processor.mathematical.operation.operand.InputOperand;

public class GrammarModel {

    public Set<ConstantOperand> constants;
    public Deque<Operation> operations;
    public Set<InputOperand> inputs;
    private Function function;

    public GrammarModel(Function function, Set<InputOperand> inputs,
            Set<ConstantOperand> constants, Deque<Operation> operations) {
        this.operations = operations;
        this.constants = constants;
        this.function = function;
        this.inputs = inputs;
    }

    public int getPrecision() {
        return function.context().precision(); 
    }
    
    public RoundingMode getRoundingMode() {
        return function.context().roundingMode();
    }
    
    public String getName() {
        return function.name();
    }

    public String getFormula() {
        return function.formula();
    }

    public String getFormulaFormat() {
        String template = function.formula();
        
        int argIndex = 1;
        for (InputOperand input : inputs) {
            template = template.replaceAll(input.getValue(), "%" + argIndex++ + "\\$f");
        }
        
        return template;
    }

    public NumberType getType() {
        return function.using();
    }

    
    
    public Set<InputOperand> getInputs() {
        return inputs;
    }

    public Deque<Operation> getOperations() {
        return operations;
    }

    public Operation getLastOperation() {
        return operations.getLast();
    }
}
