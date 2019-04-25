package com.sales.taxes.exceptions;

public class SalesTaxesFileReadException extends SalesTaxesException {

    public SalesTaxesFileReadException() {

    }

    public SalesTaxesFileReadException(String message) {
        super(message);
    }
    public SalesTaxesFileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
