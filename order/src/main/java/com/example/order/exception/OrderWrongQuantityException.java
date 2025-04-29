package com.example.order.exception;

public class OrderWrongQuantityException extends RuntimeException {

    public OrderWrongQuantityException() {
        super("Not enough or wrong quantity");
    }
}
