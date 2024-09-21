package com.example.LoggingFramework;

public class ConsoleLoggerObserver implements LogObserver {
    @Override
    public void log(String message) {
        System.out.println("write to console " + message);
    }
}
