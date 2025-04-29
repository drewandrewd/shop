package com.example.order.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("Order with id " + id + " was not found");
    }

    public OrderNotFoundException() {
        super("Orders were not found");
    }
}
