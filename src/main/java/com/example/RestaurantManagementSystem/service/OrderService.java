package com.example.RestaurantManagementSystem.service;

import com.example.RestaurantManagementSystem.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    Map<Integer, List<Order>> mapOfTableNumberToListOfOrders;
    AdminService adminService;

    private static OrderService instance;
    CustomerService customerService;

    private OrderService() {
        mapOfTableNumberToListOfOrders = new HashMap<>();
        adminService = AdminService.getInstance();
        customerService = CustomerService.getInstance();
    }

    public static OrderService getInstance() {
        if (instance == null)
            instance = new OrderService();
        return instance;
    }

    public void addMenuItemsToOrder(int tableNumber, int quantity, String name) {
        if (!mapOfTableNumberToListOfOrders.containsKey(tableNumber)) {
            Order order = new Order();
            Map<String, Integer> menuItemNameQuantityMap = order.getMenuItemNameQuantity();
            menuItemNameQuantityMap.put(name, quantity);
            order.setTableNumber(tableNumber);
            order.setOrderStatus(OrderStatus.PENDING);
            order.setOrderNumber(1);
            ArrayList<Order> list = new ArrayList<>();
            list.add(order);
            mapOfTableNumberToListOfOrders.put(tableNumber, list);
        } else {
            List<Order> orderList = mapOfTableNumberToListOfOrders.get(tableNumber);
            boolean isAtleastOneOrderPending = false;
            for(Order order : orderList) {
                if (order.getOrderStatus().equals(OrderStatus.PENDING)) {
                    Map<String, Integer> menuItemNameQuantityMap = order.getMenuItemNameQuantity();
                    menuItemNameQuantityMap.put(name, quantity);
                    isAtleastOneOrderPending = true;
                    break;
                }
            }

            if (!isAtleastOneOrderPending) {
                Order order = new Order();
                Map<String, Integer> menuItemNameQuantityMap = order.getMenuItemNameQuantity();
                menuItemNameQuantityMap.put(name, quantity);
                order.setTableNumber(tableNumber);
                order.setOrderStatus(OrderStatus.PENDING);
                order.setOrderNumber(orderList.size()+1);
                orderList.add(order);
            }
        }
    }

    public void removeMenuItemsFromOrder(int tableNumber, String name) {
        // assuming you cannot remove the menu items of an already placed order
        // you can only remove the menu items of pending order
        List<Order> orderList = mapOfTableNumberToListOfOrders.get(tableNumber);
        for(Order order : orderList) {
            if (order.getOrderStatus().equals(OrderStatus.PENDING)) {
                Map<String, Integer>  menuItemNameQuantityMap = order.getMenuItemNameQuantity();
                if (!menuItemNameQuantityMap.containsKey(name)) {
                    // throw exception saying no such menu item in this order
                    // assuming there can only be 1 pending order and not more than 1

                } else {
                    menuItemNameQuantityMap.remove(name);
                }
            }
        }
    }

    public void updateMenuItemQuantityInOrder(int tableNumber, String name, int quantity) {
        // assuming you cannot change the quantity of an already placed order
        // you can only change the quantity of pending order
        List<Order> orderList = mapOfTableNumberToListOfOrders.get(tableNumber);
        for(Order order : orderList) {
            if (order.getOrderStatus().equals(OrderStatus.PENDING)) {
                Map<String, Integer>  menuItemNameQuantityMap = order.getMenuItemNameQuantity();
                if (!menuItemNameQuantityMap.containsKey(name)) {
                    // throw exception saying no such menu item in this order
                    // assuming there can only be 1 pending order and not more than 1

                } else {
                    menuItemNameQuantityMap.put(name, quantity);
                }
            }
        }
    }

    public int placeOrder(int tableNumber) {
        int orderNumber = -1;
        if (!mapOfTableNumberToListOfOrders.containsKey(tableNumber)) {
            // throw exception that there is no pending order for this table number, would you want
            // add menu items to a new order and place order for that
            return -1;
        }
        List<Order> orderList = mapOfTableNumberToListOfOrders.get(tableNumber);
        for(Order order : orderList) {
            if (order.getOrderStatus().equals(OrderStatus.PENDING)) {
                order.setOrderStatus(OrderStatus.PLACED);
                orderNumber = order.getOrderNumber();
                break;
            }
        }
        if(orderNumber == -1) {
            // throw some exception saying error occurred and could not place the order.
        }
        return orderNumber;
    }

    public Bill generateBill(int tableNumber, PaymentType paymentType) {
        Bill bill = new Bill();
        int totalPrice = 0;
        int additionalCharges = 0;
        int taxes = 0;
        List<Order> orderList = mapOfTableNumberToListOfOrders.get(tableNumber);
        for(Order order : orderList) {
            if (order.getOrderStatus().equals(OrderStatus.PLACED)) {
                Map<String, Integer>  menuItemNameQuantityMap = order.getMenuItemNameQuantity();
                for(String menuItemName : menuItemNameQuantityMap.keySet()) {
                    int quantity = menuItemNameQuantityMap.get(menuItemName);
                    Map<String, MenuItem> mapMenuItemsByName = adminService.mapMenuItemsByName;
                    MenuItem menuItem = mapMenuItemsByName.get(menuItemName);
                    int price = menuItem.getPrice();
                    totalPrice = totalPrice + quantity*price;
                }
            }
        }

        if (paymentType.equals(PaymentType.CREDIT_CARD)) {
            additionalCharges = additionalCharges + (totalPrice/100);
        }

        taxes = (totalPrice*18)/100;

        bill.setBillId(1); // generate auto incrementer random
        bill.setPaymentType(paymentType);
        bill.setAdditionalCharges(additionalCharges);
        bill.setTotalAmount(totalPrice + additionalCharges + taxes);
        bill.setTableNumber(tableNumber);

        Map<Integer, User> mapOfUserByTableNumber = customerService.mapOfUserByTableNumber;
        User user = mapOfUserByTableNumber.get(tableNumber);
        bill.setUserId(user.getUserId());
        return bill;
    }

    public void payBill(int tableNumber) {
        mapOfTableNumberToListOfOrders.remove(tableNumber);
        Map<Integer, User> mapOfUserByTableNumber = customerService.mapOfUserByTableNumber;
        mapOfUserByTableNumber.remove(tableNumber);
        Map<Integer, Table> mapOfTablesByTableNumber = adminService.mapOfTablesByTableNumber;
        Table table = mapOfTablesByTableNumber.get(tableNumber);
        table.setStatusType(StatusType.VACANT);
    }

}
