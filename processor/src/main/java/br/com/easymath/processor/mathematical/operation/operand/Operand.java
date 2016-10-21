package br.com.easymath.processor.mathematical.operation.operand;

import br.com.easymath.annotations.NumberType;

public interface Operand {

    public String getId();
    public NumberType getType();
    public String getValue();
    
}
