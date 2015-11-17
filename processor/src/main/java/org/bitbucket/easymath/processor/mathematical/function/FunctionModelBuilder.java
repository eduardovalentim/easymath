package org.bitbucket.easymath.processor.mathematical.function;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bitbucket.easymath.annotations.Function.NumberType;

public class FunctionModelBuilder {

    private static final Logger LOGGER = LogManager.getLogger(FunctionModelBuilder.class);

    private FunctionModel model;

    private String name;

    private String expression;

    private NumberType type;

    private FunctionConstants constants;

    public Set<String> inputs;

    public FunctionModelBuilder() {
        super();
        this.inputs = new HashSet<>();
    }

    public FunctionModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public FunctionModelBuilder withFormula(String expression) {
        this.expression = expression;
        return this;
    }

    public FunctionModelBuilder withType(NumberType type) {
        this.type = type;
        return this;
    }

    public FunctionModelBuilder withConstants(FunctionConstants constants) {
        this.constants = constants;
        return this;
    }

    public FunctionModel build() {
        String strType = type == NumberType.BIGDECIMAL ? "BigDecimal" : "double";
        model = new FunctionModel(name, expression, strType, inputs);

        Stack<String> terms = model.getTerms();
        while (terms.size() > 0) {
            String term = terms.pop();
        }

        return model;
    }
}
