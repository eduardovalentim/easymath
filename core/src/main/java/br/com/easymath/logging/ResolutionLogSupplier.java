package br.com.easymath.logging;

import java.util.Locale;
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
        // The Locale is statically defined to determine a constant behavior of
        // the log format
        return String.format(Locale.US, formula, args);
    }

}
