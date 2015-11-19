package org.bitbucket.easymath.processor.mathematical.operation;

import org.bitbucket.easymath.annotations.NumberType;

public interface Operation {

    public String getId();
    public String getOperator();
    public NumberType getType();

}
