package com.example.product.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Products are not found");
    }

    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " is not found");
    }
}
