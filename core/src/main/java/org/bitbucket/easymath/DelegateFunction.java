package org.bitbucket.easymath;

import java.lang.reflect.Method;
import java.math.MathContext;

public class DelegateFunction implements Function {

    private Object target;
    private Method method;

    public DelegateFunction(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public DelegateFunction(Method method) {
        this(null, method);
    }
    
    @Override
    public String getName() {
        return method.getName();
    }

    @Override
    public Number compute(MathContext mc, Number... args) {
        try {
            return (Number) method.invoke(target, (Object[])args);
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Error invoking function '%s' with args {%s}", getName(), args), ex);
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DelegateFunction [method=" + method + "]";
    }
    
}
