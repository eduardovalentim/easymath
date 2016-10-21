package br.com.easymath.processor.mathematical.operation;

import br.com.easymath.annotations.NumberType;

public interface Operation {

    public String getId();
    public String getOperator();
    public NumberType getType();
    public String getText();

}
