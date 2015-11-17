package org.bitbucket.easymath.processor.mathematical.function;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.bitbucket.easymath.annotations.Function;
import org.bitbucket.easymath.annotations.Function.NumberType;

public class FunctionConstants {

    public static Map<Function.NumberType, String> TYPE2JAVA = new HashMap<Function.NumberType, String>() {
        private static final long serialVersionUID = 1L;
        {
            put(Function.NumberType.BIGDECIMAL, "BigDecimal");
            put(Function.NumberType.DOUBLE, "double");
        }
    };

    private Map<NumberType, Set<String>> constants;

    public FunctionConstants() {
        super();
        this.constants = new HashMap<>();
    }

    public boolean add(NumberType key, String value) {
        Set<String> values = constants.get(key);

        if (values == null) {
            values = new HashSet<>();
            constants.put(key, values);
        }

        return values.add(value);
    }

    public Set<NumberType> getTypes() {
        return constants.keySet();
    }

    public Set<String> getValues(NumberType type) {
        return constants.get(type);
    }
    
    public String declareType(NumberType type) {
        return TYPE2JAVA.get(type);
    }
    
    public static String declareValue(String value) {
        return StringUtils.replace(value, ".", "_");
    }
}
