package com.example.loggerlib.model;


import com.example.loggerlib.sink.Sink;
import com.example.loggerlib.sink.SinkType;
import com.example.loggerlib.util.LogFormatter;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class AsyncLogger implements ILoggerType{

    private Sink sink;
    private LogFormatter logFormatter;
    private BlockingDeque<Message> queue;
    private LogLevel logLevel;
    private SinkType sinkType;

    //We can handle buffer capacity as well here. In case we want to restrict the app to write limited number of string length

    public AsyncLogger(Sink sink, LogLevel logLevel, SinkType sinkType) {
        this.sink = sink;
        this.queue = new LinkedBlockingDeque<>();
        this.logLevel = logLevel;
        this.sinkType =  sinkType;
        initiateQueueDraining();
    }

    private void initiateQueueDraining() {
        new Thread(()-> {
            try {
                this.drainQueue();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private void drainQueue() throws InterruptedException {
        do{
            Message message = queue.take();
            String logContent = LogFormatter.createFormattedLog(message, sinkType);
            sink.flush(logContent,message.getNamespace());
        }while (true);
    }

    @Override
    public void handleLogMessage(Message message) {

        queue.offer(message);
    }
}
