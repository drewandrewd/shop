package com.example.product.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super("Categories were not found");
    }

    public CategoryNotFoundException(Long id) {
        super("Category with id " + id + " was not found");
    }
}
