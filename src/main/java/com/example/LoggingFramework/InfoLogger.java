package com.example.LoggingFramework;

public class InfoLogger extends AbstractLogger {

    public InfoLogger(int level) {
        this.level = level;
    }
    @Override
    protected void display(String message, LogSubject logSubject) {
        String msg = ("INFO : " + message);
        logSubject.notifyAllObserver(1, msg);
    }
}
