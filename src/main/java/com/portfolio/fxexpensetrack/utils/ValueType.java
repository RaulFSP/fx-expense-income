package com.portfolio.fxexpensetrack.utils;

public enum ValueType {

    EXPENSE("expense"),
    INCOME("income");

    private String value;

    ValueType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
