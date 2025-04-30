package com.example.product.exception;

public class ProductLowerZeroQuantityException extends RuntimeException {

    public ProductLowerZeroQuantityException() {
        super("Quantity can't be lower than zero");
    }
}
