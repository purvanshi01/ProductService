package com.example.RestaurantManagementSystem.models;

import java.util.HashMap;
import java.util.Map;

public class Order {
    OrderStatus orderStatus;
    int tableNumber;

    int orderNumber;

    Map<String, Integer> menuItemNameQuantity = new HashMap<>();

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Map<String, Integer> getMenuItemNameQuantity() {
        return menuItemNameQuantity;
    }

    public void setMenuItemNameQuantity(Map<String, Integer> menuItemNameQuantity) {
        this.menuItemNameQuantity = menuItemNameQuantity;
    }
}
