package com.example.ProducerConsumer;

import java.util.Queue;
import java.util.LinkedList;

public class Data {
    Queue<String> queue;
    int capacity;

    public Data(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<>();
    }

    public void publish(String msg) {
        String name = Thread.currentThread().getName();
        if (queue.size() == capacity) {
            System.out.println("Queue Full " + name + " waiting for message to be consumed!!");
            return;
        }
        queue.add(msg);
        System.out.println("Msg published " + msg);
        System.out.println("Queue " + queue);
        System.out.println();
    }

    public void consume() {
        String name = Thread.currentThread().getName();
        if (queue.size() == 0) {
            System.out.println("Queue empty " + name + " waiting for message to be published!!");
            return;
        }
        String msg = queue.poll();
        System.out.println(name + "has consumed Msg :: " + msg);
        System.out.println("Queue " + queue);
        System.out.println();
    }
}
