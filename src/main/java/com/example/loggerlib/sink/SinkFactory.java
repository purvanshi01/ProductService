package com.example.loggerlib.sink;


import com.example.loggerlib.config.SinkMeta;
import com.example.loggerlib.exception.InvalidInputException;

public enum SinkFactory {
    INSTANCE;

    public Sink createSink(SinkType sinkType, SinkMeta sinkMeta) throws InvalidInputException {
        switch (sinkType){
            case FILE -> {
                return new SinkFile(sinkMeta.getFilePath());
            }
            case STDOUT -> {
                return new SinkStdout();
            }
            case DATABASE -> {
                return new SinkDatabase(sinkMeta.getDbConfiguration());
            }
        }
        throw new InvalidInputException("Invalid sink type provided");
    }
}
