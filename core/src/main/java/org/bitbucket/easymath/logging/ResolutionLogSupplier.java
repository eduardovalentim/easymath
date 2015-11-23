package org.bitbucket.easymath.logging;

import java.util.function.Supplier;

public class ResolutionLogSupplier implements Supplier<String> {

    private String formula;
    private Object[] args;

    public ResolutionLogSupplier(String formula, Object... args) {
        this.formula = formula;
        this.args = args;
    }
    
    @Override
    public String get() {
        return String.format(formula, args);
    }

}
