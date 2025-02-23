package com.example.snakeGame.exceptions;

public class InvalidInputException extends Exception {
    String message;
    public InvalidInputException(String message) {
        super(message);
        this.message = message;
    }
}
