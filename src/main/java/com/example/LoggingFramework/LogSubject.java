package com.example.LoggingFramework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogSubject {
    Map<Integer, List<LogObserver>> logObservers = new HashMap<>();

    void addObserver(int level, LogObserver logObserver) {
        List<LogObserver> logObserverList = logObservers.getOrDefault(level, new ArrayList<>());
        logObserverList.add(logObserver);
        logObservers.put(level, logObserverList);
    }

    void notifyAllObserver(int level, String message) {
        for(int key : logObservers.keySet()) {
            if (key == level) {
                logObservers.get(key).forEach(observer -> observer.log(message));
            }
        }
    }
}
