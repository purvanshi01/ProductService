package com.example.ProducerConsumerWithSynchronization;

public class Consumer implements Runnable {
    Data data;

    public Consumer(Data data) {
        this.data = data;
    }

    @Override public void run() {
        try {
            while(true) {
                Thread.sleep(500);
                data.consume();
            }
        } catch (InterruptedException e) {}
    }
}
