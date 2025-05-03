package com.example.eventDriver;

import com.example.eventDriver.enumsAndModels.EventType;

public class EventManagerFactory {
    public static EventManager getEventManager(EventType eventType, int bufferSize) {
        if (eventType == EventType.Async) {
            return new AsyncEventService(bufferSize);
        }
        else {
            return new SyncEventService();
        }
    }
}
