package com.example.LoggingFramework;

public class DebugLogger  extends AbstractLogger {

    public DebugLogger(int level) {
        this.level = level;
    }
    @Override
    protected void display(String message, LogSubject logSubject) {
        String msg = ("DEBUG : " + message);
        logSubject.notifyAllObserver(3, msg);
    }
}
