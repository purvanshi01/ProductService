package com.example.loggerlib.sink;


import com.example.loggerlib.model.Namespace;

public interface Sink {
    void flush(String content, Namespace namespace) throws InterruptedException;
}
