package org.bitbucket.easymath.processor.mathematical.resolution;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.bitbucket.easymath.processor.mathematical.operation.Operation;
import org.bitbucket.easymath.processor.mathematical.operation.operand.InputOperand;

public class FormulaResolution {

    private int position;
    private int precision;
    private String formula;
    
    private Map<String, String> operationHolder;
    /**
     * Input position holder
     */
    private Map<String, String> inputHolder;

    public FormulaResolution(String formula, int precision) {
        if (formula == null) {
            throw new IllegalArgumentException("Argument 'formula' cannot be null.");
        }
        if (precision < 0) {
            throw new IllegalStateException("Argument 'precision' cannot be less than zero.");
        }

        this.position = 0;
        this.formula = formula;
        this.precision = precision;
        
        this.operationHolder = new LinkedHashMap<String, String>();
        this.inputHolder = new LinkedHashMap<String, String>();
    }

    public String format(Set<InputOperand> inputs) {
        String template = formula;
        for (InputOperand input : inputs) {
            String tag = nextTag();
            inputHolder.put(tag, input.getValue());
            template = StringUtils.replace(template, input.getValue(), tag);
        }

        return template;
    }

    public String format(Operation operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Argument 'operation' cannot be null.");
        }
        
        String id = operation.getId();
        String text = operation.getText();

        for (Entry<String, String> entry : operationHolder.entrySet()) {
            text = StringUtils.replaceOnce(text, entry.getValue(), entry.getKey());
        }

        operationHolder.put(id, text);

        formula = StringUtils.replaceOnce(formula, text, id);

        return formula;
    }
    
    public Collection<String> getFormatArguments() {
        return inputHolder.values();
    }

    private String nextTag() {
        return "%" + ++position + "$." + precision + "f";
    }
}
