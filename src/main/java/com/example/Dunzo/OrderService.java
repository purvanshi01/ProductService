package com.example.Dunzo;

import com.example.Dunzo.models.DeliveryPartner;
import com.example.Dunzo.models.Order;
import com.example.Dunzo.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class OrderService {
    Map<String, Order> orderLookup;
    BlockingQueue<DeliveryPartner> deliveryPartners;
    private static OrderService instance;

    public static OrderService getInstance(BlockingQueue<DeliveryPartner> queue) {
        if (instance == null) {
            instance = new OrderService(queue);
        }
        return instance;
    }

    private OrderService(BlockingQueue<DeliveryPartner> queue) {
        this.orderLookup = new HashMap<>();
        this.deliveryPartners = queue;
    }

    public void placeOrder(User user1, User user2, String orderName) throws InterruptedException {
        Order newOrder = new Order(orderName, OrderStatus.Placed, user1, user2, null);
        if (orderLookup.containsKey(orderName)) {
            // throw exception for duplicate order with name id
        }
        orderLookup.put(orderName, newOrder);
    }
}
