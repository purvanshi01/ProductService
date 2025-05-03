package com.example.eventDriver;

public class SyncEventService implements EventManager {

    @Override
    public void executeEvent(String eventID) {
        System.out.println("Sync event with id: " + eventID + " is executing...");
    }

    @Override
    public void cancelEvent(String eventID) {

    }

    @Override
    public void getEventStatus(String eventID) {

    }
}
