package com.example.SellerInventory.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seller {
    private int id;
    private Map<Product, Integer> productQuantity = new HashMap<>();

    private int rating;

    private Map<Warehouse, List<Product>> productsInWarehouse = new HashMap<>();

    public Seller(int id, Map<Product, Integer> productQuantity, int rating, Map<Warehouse, List<Product>> productsInWarehouse) {
        this.id = id;
        this.productQuantity = productQuantity;
        this.rating = rating;
        this.productsInWarehouse = productsInWarehouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Product, Integer> getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Map<Product, Integer> productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Map<Warehouse, List<Product>> getProductsInWarehouse() {
        return productsInWarehouse;
    }

    public void setProductsInWarehouse(Map<Warehouse, List<Product>> productsInWarehouse) {
        this.productsInWarehouse = productsInWarehouse;
    }
}
