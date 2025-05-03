package com.example.ProducerConsumerUsingBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerUsingBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<String> q = new ArrayBlockingQueue<>(10);
        Thread producer = new Thread(new Producer(q));
        producer.start();
        for(int i=1;i<=5;i++){
            new Thread(new Consumer(q), "Consumer "+i).start();
        }
    }
}
