package com.example.loggerlib.model;


import com.example.loggerlib.exception.InvalidInputException;
import com.example.loggerlib.sink.Sink;
import com.example.loggerlib.sink.SinkType;

public enum LoggerTypeFactory {
    INSTANCE;

    public ILoggerType createLogger(LoggerType loggerType, Sink sink, LogLevel logLevel, SinkType sinkType) throws InvalidInputException {
        switch (loggerType){
            case SYNC -> {
                return new SyncLogger(sink,sinkType);
            }
            case ASYNC -> {
                return new AsyncLogger(sink,logLevel,sinkType);
            }

        }
        throw new InvalidInputException("Invalid sink type provided");
    }
}
