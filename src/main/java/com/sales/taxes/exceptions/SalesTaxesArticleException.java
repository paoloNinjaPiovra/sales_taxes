package com.sales.taxes.exceptions;

public class SalesTaxesArticleException extends SalesTaxesException {

    public SalesTaxesArticleException() {

    }

    public SalesTaxesArticleException(String message) {
        super(message);
    }
    public SalesTaxesArticleException(String message, Throwable cause) {
        super(message, cause);
    }
}
