package com.example.product.exception;

public class BrandNotFoundException extends RuntimeException {

    public BrandNotFoundException() {
        super("Brands are not found");
    }

    public BrandNotFoundException(Long id) {
        super("Brand with id " + id + " is not found");
    }
}
