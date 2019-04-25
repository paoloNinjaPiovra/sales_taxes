package com.sales.taxes.enums;

public enum TaxFree {
    BOOK("book"),
    CHOCOLATE("chocolate"),
    CHOCOLATES("chocolates"),
    HEADACHE("headache");

    private String value;

    public String getValue() {
        return this.value;
    }

    TaxFree(String name) {
        this.value = name;
    }

}
