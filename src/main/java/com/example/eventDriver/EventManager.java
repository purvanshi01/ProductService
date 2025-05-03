package com.example.eventDriver;

public interface EventManager {
    void executeEvent(String eventID);
    void cancelEvent(String eventID);
    void getEventStatus(String eventID);
}
