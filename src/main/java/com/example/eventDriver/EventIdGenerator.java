package com.example.eventDriver;

import java.util.concurrent.atomic.AtomicInteger;

public class EventIdGenerator {
    private static AtomicInteger eventId = new AtomicInteger(1);
    public static String getEventId() {
        return "event" + String.valueOf(eventId.getAndIncrement());
    }
}
