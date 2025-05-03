package com.example.eventDriver.enumsAndModels;

import java.util.HashMap;
import java.util.Map;

public enum EventRepository {
    INSTANCE;
    Map<String, Event> eventLookup;

    EventRepository() {
        eventLookup = new HashMap<>();
    }

    public Event read(String eventID) {
        return eventLookup.get(eventID);
    }

    public void write(String eventID, Event event) {
        eventLookup.put(eventID, event);
    }
}
