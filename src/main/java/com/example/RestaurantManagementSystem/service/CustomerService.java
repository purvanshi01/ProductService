package com.example.RestaurantManagementSystem.service;

import com.example.RestaurantManagementSystem.models.*;

import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    public Map<Integer, User> mapOfUserByTableNumber;
    public Map<String, User> mapOfUserByPhoneNumber;

    AdminService adminService;
    private static CustomerService instance;

    private CustomerService() {
        mapOfUserByTableNumber = new HashMap<>();
        mapOfUserByPhoneNumber = new HashMap<>();
        adminService = AdminService.getInstance();
    }

    public static CustomerService getInstance() {
        if (instance == null)
            instance = new CustomerService();
        return instance;
    }

    public void browseMenus(VegNonVeg vegNonVeg) {
        Map<String, MenuItem> menu = new HashMap<>();
        if(vegNonVeg.equals(VegNonVeg.NON_VEG))
            menu = adminService.nonVegMapMenuItemsByName;
        if(vegNonVeg.equals(VegNonVeg.VEG))
            menu = adminService.vegMapMenuItemsByName;

        System.out.println("This menu contains the below menu items: ");
        for(String menuItemName : menu.keySet()) {
            System.out.println(menuItemName);
        }
    }
    public int allocateTable(int capacity, String phone) {
        User user = mapOfUserByPhoneNumber.get(phone);
        int tableNumber = adminService.allocateTable(capacity);
        mapOfUserByTableNumber.put(tableNumber, user);
        return tableNumber;
    }

    public void reserveTable(int tableNumber, String phone) {
        Table table = adminService.mapOfTablesByTableNumber.get(tableNumber);
        table.setStatusType(StatusType.RESERVED);
        User user = mapOfUserByPhoneNumber.get(phone);
        mapOfUserByTableNumber.put(tableNumber, user);
    }

}
