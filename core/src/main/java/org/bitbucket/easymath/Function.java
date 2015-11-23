package org.bitbucket.easymath;

import java.math.MathContext;

public interface Function {

    public String getName();
    
    public Number compute(MathContext mc, Number... args);
}
