package com.example.eventDriver;

import com.example.eventDriver.enumsAndModels.Event;
import com.example.eventDriver.enumsAndModels.EventRepository;
import com.example.eventDriver.enumsAndModels.EventStatus;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AsyncEventService implements EventManager {
    private BlockingQueue<String> blockingQueue;
    private EventRepository eventRepository;

    public AsyncEventService(int bufferSize) {
        blockingQueue = new ArrayBlockingQueue<>(bufferSize);
        eventRepository = EventRepository.INSTANCE;
        initBackgroundProcessing();
    }

    private void initBackgroundProcessing() {
        new Thread(this::consume).start();
    }

    private void consume() {
        do {
            try {
                Thread.sleep(1000);
                String eventId =  blockingQueue.take();
                Event event = eventRepository.read(eventId);
                event.setEventsStatus(EventStatus.Completed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while(true);
    }
    @Override
    public void executeEvent(String eventID) {
        Event event = new Event();
        event.setEvenID(eventID);
        eventRepository.write(eventID, event);
        boolean isAddedSuccessfully = blockingQueue.offer(eventID);
        if (isAddedSuccessfully) {
            event.setEventsStatus(EventStatus.InProgress);
            System.out.println("Order successfully placed and is in progress...");
        }
    }

    @Override
    public void cancelEvent(String eventID) {
        Event event = eventRepository.read(eventID);
        boolean remove = blockingQueue.remove(eventID);
        if (remove) {
            event.setEventsStatus(EventStatus.Cancelled);
            System.out.println("Order successfully cancelled...");
        }
    }

    @Override
    public void getEventStatus(String eventID) {
        Event event = eventRepository.read(eventID);
        EventStatus eventsStatus = event.getEventsStatus();
        System.out.println("Order status is " + eventsStatus.name());
    }
}
