package com.example.loggerlib.impl;


import com.example.loggerlib.config.LoggerConfiguration;
import com.example.loggerlib.exception.InvalidInputException;
import com.example.loggerlib.model.*;
import com.example.loggerlib.sink.Sink;
import com.example.loggerlib.sink.SinkFactory;

public class LoggerImpl implements Logger {

    private LoggerConfiguration loggerConfiguration;
    private Sink sink;
    private ILoggerType loggerInstance;
    private LogLevel logLevel;

    public LoggerImpl(LoggerConfiguration loggerConfiguration) throws InvalidInputException {
        this.loggerConfiguration = loggerConfiguration;
        this.logLevel = loggerConfiguration.getLoglevel();
        initiateInstances();
    }

    private void initiateInstances() throws InvalidInputException {
        this.sink = SinkFactory.INSTANCE.createSink(this.loggerConfiguration.getSinkType(),this.loggerConfiguration.getSinkMeta());
        this.loggerInstance = LoggerTypeFactory.INSTANCE.createLogger(this.loggerConfiguration.getLoggerType(),this.sink,
                this.loggerConfiguration.getLoglevel(),this.loggerConfiguration.getSinkType());
    }


    @Override
    public void updateLoglevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void debug(Message message) throws InterruptedException {
        if(logLevel.isLogLevelHasHighPriority(LogLevel.DEBUG)){
            message.setLogLevel(logLevel);
            loggerInstance.handleLogMessage(message);
        }
    }

    @Override
    public void info(Message message) throws InterruptedException {
        if(logLevel.isLogLevelHasHighPriority(LogLevel.INFO)){
            message.setLogLevel(logLevel);
            loggerInstance.handleLogMessage(message);
        }
    }

    @Override
    public void fatal(Message message) throws InterruptedException {
        if(logLevel.isLogLevelHasHighPriority(LogLevel.FATAL)){
            message.setLogLevel(logLevel);
            loggerInstance.handleLogMessage(message);
        }
    }

    @Override
    public void error(Message message) throws InterruptedException {
        if(logLevel.isLogLevelHasHighPriority(LogLevel.ERROR)){
            message.setLogLevel(logLevel);
            loggerInstance.handleLogMessage(message);
        }
    }

    @Override
    public void warn(Message message) throws InterruptedException {
        if(logLevel.isLogLevelHasHighPriority(LogLevel.WARN)){
            message.setLogLevel(logLevel);
            loggerInstance.handleLogMessage(message);
        }
    }

}
