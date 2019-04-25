package com.sales.taxes.exceptions;

public class SalesTaxesException extends Exception {

    public SalesTaxesException() {

    }

    public SalesTaxesException(String message) {
        super(message);
    }
    public SalesTaxesException(String message, Throwable cause) {
        super(message, cause);
    }
}
