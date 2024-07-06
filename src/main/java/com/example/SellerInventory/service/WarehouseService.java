package com.example.SellerInventory.service;

import com.example.SellerInventory.exceptions.SellerDoesNotExist;
import com.example.SellerInventory.exceptions.WarehouseDoesNotExist;
import com.example.SellerInventory.models.Warehouse;

import java.util.*;

public class WarehouseService {
    Map<Integer, Warehouse> listOfWarehouses = new HashMap<>();

    private static WarehouseService instance;

    public static WarehouseService getInstance() {
        if (instance == null)
            instance = new WarehouseService();
        return instance;
    }

    public void addWarehouse(int warehouseId) {
        listOfWarehouses.put(warehouseId, new Warehouse(warehouseId, new HashMap<>()));
    }

    public void addCapacity(int warehouseId, Date date, int capacity) throws WarehouseDoesNotExist {
        if (!listOfWarehouses.containsKey(warehouseId)) {
            throw new WarehouseDoesNotExist(500, "Warehouse with id: " + warehouseId + " does not exist");
        }

        Warehouse warehouse = listOfWarehouses.get(warehouseId);
        warehouse.getDateCapacity().put(date, capacity);
    }

    public void warehouseCapacity (int warehouseId, Date startDate) {
        Warehouse warehouse = listOfWarehouses.get(warehouseId);
        Map<Date, Integer> dateCapacity = warehouse.getDateCapacity();
        Map<Date, Integer> sortedMap = new TreeMap<>(dateCapacity);
        int count = 0;
        for (Date date : sortedMap.keySet()) {
            if (date.compareTo(startDate) >= 0 && count <= 10) {
                System.out.println("Date : " + date + " Capacity : " + sortedMap.get(date));
                count++;
            }
        }
    }
}
