package com.example.loggerlib.model;

public enum LogLevel {
    DEBUG(0),
    INFO(1),
    WARN(2),
    ERROR(3),
    FATAL(4);

    int logLevel;

    LogLevel(int logLevel){
        this.logLevel = logLevel;
    }

    public boolean isLogLevelHasHighPriority(LogLevel otherLogLevel){
        int curLogLevel = this.logLevel;
        return otherLogLevel.logLevel >= curLogLevel;
    }



}
