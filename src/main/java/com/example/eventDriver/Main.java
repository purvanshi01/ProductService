package com.example.eventDriver;

import com.example.eventDriver.enumsAndModels.EventType;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EventGenerator synceEventGenerator = new EventGenerator(EventType.Sync, 0);
        List<Thread> syncThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                try {
                    synceEventGenerator.executeEvent();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            syncThreads.add(t);
            t.start();
        }

        EventGenerator asyncEventGenerator = new EventGenerator(EventType.Async, 100);
        List<Thread> asyncThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                try {
                    asyncEventGenerator.executeEvent();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            asyncThreads.add(t);
            t.start();
        }

        for (int i = 0; i < 10; i++) {
            syncThreads.get(i).join();
            asyncThreads.get(i).join();
        }
    }
}
