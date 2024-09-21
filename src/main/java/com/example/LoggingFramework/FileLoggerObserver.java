package com.example.LoggingFramework;

public class FileLoggerObserver implements LogObserver {
    @Override
    public void log(String message) {
        System.out.println("write to file " + message);
    }
}
