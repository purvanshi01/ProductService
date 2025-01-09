package com.example.loggerlib.model;


import com.example.loggerlib.sink.Sink;
import com.example.loggerlib.sink.SinkType;
import com.example.loggerlib.util.LogFormatter;

public class SyncLogger implements ILoggerType{

    private Sink sink;
    private LogFormatter logFormatter;
    private SinkType sinkType;

    public SyncLogger(Sink sink, SinkType sinkType) {
        this.sink = sink;
        this.sinkType = sinkType;
    }

    @Override
    public void handleLogMessage(Message message) throws InterruptedException {
        String logContent = LogFormatter.createFormattedLog(message,sinkType);
        sink.flush(logContent,message.getNamespace());
    }
}
