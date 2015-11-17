package org.bitbucket.easymath.annotations;

public enum NumberType {
    DOUBLE("double"), BIGDECIMAL("BigDecimal");
    
    private String text;

    NumberType(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }
}