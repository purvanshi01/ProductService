package com.example.ProducerConsumerWithSynchronization;

import java.util.Queue;
import java.util.LinkedList;

public class Data {
    Queue<String> queue;
    int capacity;

    public Data(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    public synchronized void publish(String msg) throws InterruptedException {
        String name = Thread.currentThread().getName();
        while (queue.size() == capacity) {
            System.out.println("Queue Full " + name + " waiting for message to be consumed!!");
            wait();
        }
        queue.add(msg);
        System.out.println("Msg published " + msg);
        System.out.println("Queue " + queue);
        System.out.println();
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        String name = Thread.currentThread().getName();
        while (queue.isEmpty()) {
            System.out.println("Queue empty " + name + " waiting for message to be published!!");
            wait();
        }
        String msg = queue.poll();
        System.out.println(name + " has consumed Msg :: " + msg);
        System.out.println("Queue " + queue);
        System.out.println();
        notifyAll();
    }
}
