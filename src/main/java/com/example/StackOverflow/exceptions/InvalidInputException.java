package com.example.StackOverflow.exceptions;

public class InvalidInputException extends Exception {
    private String message;
    private int code;

    public InvalidInputException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
