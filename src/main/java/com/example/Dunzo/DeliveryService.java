package com.example.Dunzo;

import com.example.Dunzo.models.Order;

import java.util.concurrent.BlockingQueue;

public class DeliveryService {
    BlockingQueue<Order> orderQueue;
    private static DeliveryService instance;

    public static DeliveryService getInstance(BlockingQueue<Order> queue) {
        if (instance == null) {
            instance = new DeliveryService(queue);
        }
        return instance;
    }

    public DeliveryService(BlockingQueue<Order> queue) {
        this.orderQueue = queue;
    }

    public void assignDeliveryPartner() throws InterruptedException {
        Order order = orderQueue.take();
        order.setOrderStatus(OrderStatus.Assigned);
    }
}
