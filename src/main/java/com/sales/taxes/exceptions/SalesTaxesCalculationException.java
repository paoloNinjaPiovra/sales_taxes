package com.sales.taxes.exceptions;

public class SalesTaxesCalculationException extends SalesTaxesException {

    public SalesTaxesCalculationException() {

    }

    public SalesTaxesCalculationException(String message) {
        super(message);
    }
    public SalesTaxesCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
