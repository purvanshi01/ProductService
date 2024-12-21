package com.example.flipkartShowBookingSystem.exceptions;

public class NumberOfSeatesGreaterThanAvailableCapacityException extends Exception {
    public NumberOfSeatesGreaterThanAvailableCapacityException(String message) {
        super(message);
    }
}
