package com.commerce.orders.exception;

public class OrderWrongTotalAmountException extends RuntimeException {
    public OrderWrongTotalAmountException(String message) {
        super(message);
    }
}
