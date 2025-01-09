package com.example.loggerlib.config;

import com.example.loggerlib.model.LogLevel;
import com.example.loggerlib.model.LoggerType;
import com.example.loggerlib.sink.SinkType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggerConfiguration {

    private SinkType sinkType;
    private SinkMeta sinkMeta;
    private LoggerType loggerType;
    private LogLevel loglevel;

    public LoggerConfiguration(SinkType sinkType, LoggerType loggerType, LogLevel loglevel) {
        this.sinkType = sinkType;
        this.loggerType = loggerType;
        this.loglevel = loglevel;
    }
}
