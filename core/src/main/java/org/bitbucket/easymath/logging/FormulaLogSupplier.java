package org.bitbucket.easymath.logging;

import java.util.function.Supplier;

public class FormulaLogSupplier implements Supplier<String> {

    private String format;
    private Object[] args;

    public FormulaLogSupplier(String format, Object... args) {
        this.format = format;
        this.args = args;
    }
    
    @Override
    public String get() {
        return String.format(format, args);
    }

}
