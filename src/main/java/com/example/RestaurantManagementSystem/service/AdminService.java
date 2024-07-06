package com.example.RestaurantManagementSystem.service;

import com.example.RestaurantManagementSystem.models.*;

import java.util.HashMap;
import java.util.Map;

public class AdminService {
    Map<Integer, Table> mapOfTablesByTableNumber;
    Map<String, MenuItem> mapMenuItemsByName;
    Map<String, MenuItem> vegMapMenuItemsByName;
    Map<String, MenuItem> nonVegMapMenuItemsByName;

    private static AdminService instance;

    private AdminService() {
        mapOfTablesByTableNumber = new HashMap<>();
        mapMenuItemsByName = new HashMap<>();
        vegMapMenuItemsByName = new HashMap<>();
        nonVegMapMenuItemsByName = new HashMap<>();
    }

    public static AdminService getInstance() {
        if (instance == null)
            instance = new AdminService();
        return instance;
    }

    public void addTable(int capacity) {
        int last = mapOfTablesByTableNumber.size();
        Table table = new Table();
        table.setTableNumber(last+1);
        table.setCapacity(capacity);
        table.setStatusType(StatusType.VACANT);
        mapOfTablesByTableNumber.put(last+1, table);
    }

    public void addMenuItems(String name, int price, VegNonVeg vegNonVeg, CategoryType categoryType) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setPrice(price);
        menuItem.setVegNonVeg(vegNonVeg);
        menuItem.setCategoryType(categoryType);
        mapMenuItemsByName.put(name, menuItem);
        if(vegNonVeg.equals(VegNonVeg.NON_VEG))
            nonVegMapMenuItemsByName.put(name, menuItem);
        if(vegNonVeg.equals(VegNonVeg.VEG))
            vegMapMenuItemsByName.put(name, menuItem);
    }

    public void removeMenuItems(String name) {
        if (!mapMenuItemsByName.containsKey(name)) {
            // throw proper exception
            return;
        }
        VegNonVeg vegNonVeg = mapMenuItemsByName.get(name).getVegNonVeg();
        mapMenuItemsByName.remove(name);
        if(vegNonVeg.equals(VegNonVeg.NON_VEG))
            nonVegMapMenuItemsByName.remove(name);
        if(vegNonVeg.equals(VegNonVeg.VEG))
            vegMapMenuItemsByName.remove(name);
    }

    public void updateMenuItemPrice(int price, String name) {
        if (!mapMenuItemsByName.containsKey(name)) {
            // throw proper exception
            return;
        }
        mapMenuItemsByName.get(name).setPrice(price);
    }

    public int allocateTable(int capacity) {
        int allocatedTableNumber = -1;
        for(int tableNumber : mapOfTablesByTableNumber.keySet()) {
            Table table = mapOfTablesByTableNumber.get(tableNumber);
            if (table.getCapacity() == capacity && table.getStatusType().equals(StatusType.VACANT)) {
                // assuming if the capacity is less or more we will not assign table
                allocatedTableNumber = tableNumber;
                table.setStatusType(StatusType.OCCUPIED);
            }
        }
        if (allocatedTableNumber == -1) {
            // throw exception that no table is empty
        }

        return allocatedTableNumber;
    }
}
