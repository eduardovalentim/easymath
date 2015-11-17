package org.bitbucket.easymath.processor.mathematical.function;

import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Set;

import org.bitbucket.easymath.annotations.NumberType;
import org.bitbucket.easymath.processor.mathematical.operation.Operation;
import org.bitbucket.easymath.processor.mathematical.operation.operand.InputOperand;

public class FunctionModel {

    public Deque<Operation> operations;
    public Set<InputOperand> inputs;
    private String formula;
    private String name;
    private NumberType type;

    public FunctionModel(String name, String formula, NumberType type, Set<InputOperand> inputs, Deque<Operation> operations) {
        this.operations = operations;
        this.inputs = inputs;
        this.formula = formula;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getFormula() {
        return formula;
    }

    public NumberType getType() {
        return type;
    }

    public Set<InputOperand> getInputs() {
        return Collections.unmodifiableSet(inputs);
    }

    public Deque<Operation> getOperations() {
        return operations;
    }
    
    public Operation getLastOperation() {
        return operations.getLast();
    }
}
