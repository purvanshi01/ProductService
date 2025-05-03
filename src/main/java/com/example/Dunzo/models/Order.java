package com.example.Dunzo.models;

import com.example.Dunzo.OrderStatus;

public class Order {
    String orderName;
    OrderStatus orderStatus;
    User sourceUser;
    User destinationUser;
    DeliveryPartner deliveryPartner;

    public Order(String orderName, OrderStatus orderStatus, User sourceUser, User destinationUser, DeliveryPartner deliveryPartner) {
        this.orderName = orderName;
        this.orderStatus = orderStatus;
        this.sourceUser = sourceUser;
        this.destinationUser = destinationUser;
        this.deliveryPartner = deliveryPartner;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(User sourceUser) {
        this.sourceUser = sourceUser;
    }

    public User getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(User destinationUser) {
        this.destinationUser = destinationUser;
    }

    public DeliveryPartner getDeliveryPartner() {
        return deliveryPartner;
    }

    public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }
}
