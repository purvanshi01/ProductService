package com.example.ProducerConsumerWithSynchronization;

public class ProducerConsumerWithSynchronization {
    public static void main(String[] args) {
        Data data = new Data(5);
        Thread producer = new Thread(new Producer(data), "producer");
        for (int i = 0; i < 5; i++) {
            new Thread(new Consumer(data), "consumer" + i).start();
        }
        producer.start();
    }
}
