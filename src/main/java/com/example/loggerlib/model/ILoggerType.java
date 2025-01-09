package com.example.loggerlib.model;


public interface ILoggerType {
    void handleLogMessage(Message message) throws InterruptedException;
}
