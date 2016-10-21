package br.com.easymath.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClassUtils {

    @SuppressWarnings("serial")
    private static final Map<Class<?>, Class<?>> NUMBERS = new HashMap<Class<?>, Class<?>>() {{
        put(byte.class, Byte.class);
        put(int.class, Integer.class);
        put(long.class, Long.class);
        put(float.class, Float.class);
        put(double.class, Double.class);
    }};
    
    public static boolean isValidJavaIdentifier(String s) {
        // an empty or null string cannot be a valid identifier
        if (s == null || s.length() == 0) {
            return false;
        }

        char[] c = s.toCharArray();
        if (!Character.isJavaIdentifierStart(c[0])) {
            return false;
        }

        for (int i = 1; i < c.length; i++) {
            if (!Character.isJavaIdentifierPart(c[i])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Numeric methods are methods that receive and return Numbers
     * 
     * @param klass
     * @return
     */
    public static List<Method> getNumericMethods(Class<?> klass) {
        if (klass == null) {
            throw new IllegalArgumentException("Argument 'klass' cannot be null.");
        }
        
        List<Method> methods = new LinkedList<>();
        
        for (Method method : klass.getMethods()) {
            boolean supported = false;
            // if the return is a number
            if (Number.class.isAssignableFrom(adapt(method.getReturnType())) ) {

                // Check if all parameters are Numbers
                for (Parameter parameter : method.getParameters()) {
                    supported = Number.class.isAssignableFrom(adapt(parameter.getType())); 
                    if (!supported) {
                        break;
                    }
                }
            }
            
            if (supported) {
                methods.add(method);
            }
        }
        
        return methods;
    }

    private static Class<?> adapt(Class<?> type) {
        Class<?> wrapper = NUMBERS.get(type);
        
        if (wrapper == null) {
            wrapper = type;
        }
        
        return wrapper; 
    }
}
