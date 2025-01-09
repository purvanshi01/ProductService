package com.example.loggerlib.sink;


import com.example.loggerlib.model.Namespace;

public class SinkFile implements Sink {
    private String filePath;

    public SinkFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void flush(String content, Namespace namespace) {

        //handle file print
    }
}
