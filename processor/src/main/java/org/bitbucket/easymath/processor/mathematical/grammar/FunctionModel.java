package org.bitbucket.easymath.processor.mathematical.grammar;

import java.math.RoundingMode;
import java.util.Deque;
import java.util.Set;

import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.NumberType;
import org.bitbucket.easymath.processor.mathematical.operation.Operation;
import org.bitbucket.easymath.processor.mathematical.operation.operand.ConstantOperand;
import org.bitbucket.easymath.processor.mathematical.operation.operand.InputOperand;
import org.bitbucket.easymath.processor.mathematical.utils.FormatUtils;

public class FunctionModel {

    public Set<ConstantOperand> constants;
    public Deque<Operation> operations;
    public Set<InputOperand> inputs;
    private String compiledFormula;
    private Function function;

    public FunctionModel(Function function, String compiledFormula, Set<InputOperand> inputs,
            Set<ConstantOperand> constants, Deque<Operation> operations) {
        this.compiledFormula = compiledFormula;
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

    public String getDeclaredFormula() {
        return function.formula();
    }

    public String getCompiledFormula() {
        return compiledFormula;
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
    
    public String getInputResolutionFormat() {
        return FormatUtils.formatFormulaInputs(compiledFormula, inputs, function.context().precision());
    }
    
    public String getOperationResolutionFormat(String operation) {
        return FormatUtils.formatFormulaOperation(compiledFormula, operation, function.context().precision());
    }

}
