package com.example.VendingMachine.exceptions;

public class ProductNotFoundException extends Exception {
    int code;
    String message;

    public ProductNotFoundException (int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
