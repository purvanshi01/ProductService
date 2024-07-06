package com.example.SellerInventory.service;

import com.example.SellerInventory.exceptions.SellerDoesNotExist;
import com.example.SellerInventory.models.Product;
import com.example.SellerInventory.models.Seller;

import java.util.HashMap;
import java.util.Map;

public class ProductService {

    private static ProductService instance;
    Map<Integer, Product> listOfProducts = new HashMap<>();

    public static ProductService getInstance() {
        if (instance == null)
            instance = new ProductService();
        return instance;
    }

    private ProductService() {
    }
}
