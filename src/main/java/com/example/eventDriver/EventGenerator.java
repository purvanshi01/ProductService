package com.example.eventDriver;

import com.example.eventDriver.enumsAndModels.EventType;

public class EventGenerator {
    EventType eventType;
    EventManager eventManager;

    public EventGenerator(EventType eventType, int bufferSize) {
        this.eventType = eventType;
        this.eventManager = EventManagerFactory.getEventManager(eventType, bufferSize);
    }

    public void executeEvent() throws InterruptedException {
        String eventId = EventIdGenerator.getEventId();
        eventManager.executeEvent(eventId);
        Thread.sleep(1000);
        eventManager.getEventStatus(eventId);
        Thread.sleep(1000);
        eventManager.cancelEvent(eventId);
        Thread.sleep(1000);
        eventManager.getEventStatus(eventId);
    }

}
