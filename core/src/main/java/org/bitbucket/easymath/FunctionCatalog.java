package org.bitbucket.easymath;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bitbucket.easymath.utils.ClassUtils;

public class FunctionCatalog {

    private Map<String, Function> catalog;
    
    public FunctionCatalog() {
        super();
        this.catalog = new HashMap<>();
        load(Math.class);
    }
    
    public Function getFunction(String name) {
        Function function = catalog.get(name);
        if (function == null) {
            throw new IllegalStateException(String.format("Function '%s' not found in catalog.", name));
        }
        
        return function;
    }
    
    public Function addFunction(Function function) {
        return catalog.put(function.getName(), function);
    }
    
    public Function removeFunction(String name) {
        return catalog.remove(name);
    }
    
    public void load(Class<?> klass) {
        List<Method> methods = ClassUtils.getNumericMethods(klass);
        for (Method method : methods) {
            addFunction(new DelegateFunction(method));
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FunctionCatalog [catalog=" + catalog + "]";
    }
}
