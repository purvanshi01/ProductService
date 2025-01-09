package com.example.loggerlib.model;


public interface Logger {

    void updateLoglevel(LogLevel logLevel);
    void debug(Message message) throws InterruptedException;
    void info(Message message) throws InterruptedException;
    void fatal(Message message) throws InterruptedException;
    void error(Message message) throws InterruptedException;
    void warn(Message message) throws InterruptedException;
}
