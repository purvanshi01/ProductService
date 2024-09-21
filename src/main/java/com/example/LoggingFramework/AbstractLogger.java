package com.example.LoggingFramework;

public abstract class AbstractLogger {
    int level;
    AbstractLogger nextLoggingLevel;
    public void setNextLoggingLevel(AbstractLogger nextLoggingLevel) {
        this.nextLoggingLevel = nextLoggingLevel;
    }

    void logMessage(int level, String message, LogSubject logSubject) {
        if(this.level <= level) {
            display(message, logSubject);
        }

        if(nextLoggingLevel != null) {
            nextLoggingLevel.logMessage(level, message, logSubject);
        }
    }
    protected abstract void display(String message, LogSubject logSubject);
}
