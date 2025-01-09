package com.example.loggerlib.sink;


import com.example.loggerlib.model.Namespace;

public class SinkStdout implements Sink {
    @Override
    public void flush(String content, Namespace namespace) throws InterruptedException {
        //handle console print
        Thread.sleep(100); //Similating console write
        System.out.println(content);
    }
}
