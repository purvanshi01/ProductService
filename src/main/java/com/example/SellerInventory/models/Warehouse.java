package com.example.SellerInventory.models;

import java.util.Date;
import java.util.Map;

public class Warehouse {
    private int warehouseId;
    private Map<Date, Integer> dateCapacity;

    public Warehouse(int warehouseId, Map<Date, Integer> dateCapacity) {
        this.warehouseId = warehouseId;
        this.dateCapacity = dateCapacity;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Map<Date, Integer> getDateCapacity() {
        return dateCapacity;
    }

    public void setDateCapacity(Map<Date, Integer> dateCapacity) {
        this.dateCapacity = dateCapacity;
    }
}
