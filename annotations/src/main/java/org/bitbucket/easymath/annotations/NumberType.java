package org.bitbucket.easymath.annotations;

public enum NumberType {
    DOUBLE("double"), BIGDECIMAL("BigDecimal"), APFLOAT("Apfloat");
    
    private String text;

    NumberType(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }
}