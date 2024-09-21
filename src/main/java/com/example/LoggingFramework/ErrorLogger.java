package com.example.LoggingFramework;

public class ErrorLogger  extends AbstractLogger {

    public ErrorLogger(int level) {
        this.level = level;
    }
    @Override
    protected void display(String message, LogSubject logSubject) {
        String msg = ("ERROR : " + message);
        logSubject.notifyAllObserver(2, msg);
    }
}