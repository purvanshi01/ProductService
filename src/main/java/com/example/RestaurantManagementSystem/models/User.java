package com.example.RestaurantManagementSystem.models;

import com.example.RestaurantManagementSystem.service.CustomerService;

public class User {
    CustomerService customerService;
    String name;
    String phone;
    int tableNumber;
    int userId;

    public User() {
        this.customerService = CustomerService.getInstance();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void addUser(String phone, String name) {
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setUserId(this.customerService.mapOfUserByPhoneNumber.size()+1);
        this.customerService.mapOfUserByPhoneNumber.put(phone, user);
    }
}
