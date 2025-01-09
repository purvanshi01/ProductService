package com.example.loggerlib.sink;


import com.example.loggerlib.model.Namespace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SinkDatabase implements Sink {
    private Map<String, List<String>> inMemoryDb;

    public SinkDatabase(Object dbConfiguration) {
        inMemoryDb =  new ConcurrentHashMap<>();
    }

    @Override
    public void flush(String content, Namespace namespace) {
        //handle database print
        if(inMemoryDb.containsKey(namespace.getAppId())){
            inMemoryDb.get(namespace.getAppId()).add(content);
        }else{
            List<String> contents = new ArrayList<>();
            contents.add(content);
            inMemoryDb.put(namespace.getAppId(), contents);
        }
    }
}
