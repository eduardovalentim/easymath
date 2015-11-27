package org.bitbucket.easymath.processor.mathematical.resolution;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
    private Map<String, String> inputHolder;
    private Map<String, String> resultHolder;

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
        this.resultHolder = new LinkedHashMap<String, String>();
    }

    public String format(Set<InputOperand> inputs) {
        for (InputOperand input : inputs) {
            String tag = nextTag();
            inputHolder.put(tag, input.getValue());
            formula = StringUtils.replace(formula, input.getValue(), tag);
        }

        return formula;
    }

    public String format(Operation operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Argument 'operation' cannot be null.");
        }
        
        String tag = nextTag();
        String id = operation.getId();
        String text = operation.getText();

        for (Entry<String, String> input : inputHolder.entrySet()) {
            text = StringUtils.replace(text, input.getValue(), input.getKey());
        }
        for (Entry<String, String> entry : operationHolder.entrySet()) {
            text = StringUtils.replaceOnce(text, entry.getValue(), entry.getKey());
        }

        operationHolder.put(id, text);
        resultHolder.put(tag, id);

        formula = StringUtils.replaceOnce(formula, text, id);

        return formula;
    }
    
    public Collection<String> getFormatArguments() {
        Collection<String> arguments = new LinkedList<String>();
        arguments.addAll(inputHolder.values());
        arguments.addAll(resultHolder.values());
        return arguments;
    }

    private String nextTag() {
        return "%" + ++position + "$." + precision + "f";
    }
}
