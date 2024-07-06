package com.example.SellerInventory.service;

import com.example.SellerInventory.exceptions.SellerAlreadyExists;
import com.example.SellerInventory.exceptions.SellerAlreadyOnboardedToWarehouse;
import com.example.SellerInventory.exceptions.SellerDoesNotExist;
import com.example.SellerInventory.models.Product;
import com.example.SellerInventory.models.Seller;
import com.example.SellerInventory.models.Warehouse;

import java.util.*;

public class SellerService {
    private WarehouseService warehouseService;
    private ProductService productService;
    Map<Integer, Seller> mapOfSellers = new HashMap<>();

    private static SellerService instance;

    public SellerService() {
        this.warehouseService = WarehouseService.getInstance();
        this.productService = ProductService.getInstance();
    }

    public static SellerService getInstance() {
        if (instance == null)
            instance = new SellerService();
        return instance;
    }
    public void registerService(int sellerId) throws SellerAlreadyExists {
        if (mapOfSellers.containsKey(sellerId)) {
            throw new SellerAlreadyExists(500, "A seller with this id: " + sellerId + " is already registered");
        }
        Seller seller = new Seller(sellerId, new HashMap<>(), 1, new HashMap<>());
        mapOfSellers.put(sellerId, seller);
    }

    public void onboardWarehouse(int sellerId, int warehouseId) throws SellerDoesNotExist, SellerAlreadyOnboardedToWarehouse {
        if (!mapOfSellers.containsKey(sellerId)) {
            throw new SellerDoesNotExist(500, "A seller with this id: " + sellerId + " is not registered");
        }
        Seller seller = mapOfSellers.get(sellerId);
        Map<Warehouse, List<Product>> warehouses = seller.getProductsInWarehouse();
        if (warehouses.containsKey(warehouseId)) {
            throw new SellerAlreadyOnboardedToWarehouse(500, "Seller already onboarded to the warehouse");
        }

        Map<Integer, Warehouse> listOfWarehouses = warehouseService.listOfWarehouses;
        Warehouse warehouse = listOfWarehouses.get(warehouseId);
        warehouses.put(warehouse, new ArrayList<>());
    }

    public void addProduct(int productId, String productName, int sellerId) throws SellerDoesNotExist {

        if (!mapOfSellers.containsKey(sellerId)) {
            throw new SellerDoesNotExist(500, "A seller with this id: " + sellerId + " is not registered");
        }

        Product product = new Product(productName, productId, 0);
        Seller seller = mapOfSellers.get(sellerId);
        Map<Product, Integer> productIntegerHashMap = seller.getProductQuantity();
        productIntegerHashMap.put(product, 0);
        productService.listOfProducts.put(productId, product);
    }

    public Map<Integer, Date> getSlots(int sellerId, Map<Integer, Integer> productIdQuantity, Date startDate) {
        Map<Integer, Date> warehouseDateMap = new HashMap<>();

        Seller seller = mapOfSellers.get(sellerId);

        int totalCapacity = 0;

        for (int productId : productIdQuantity.keySet()) {
            int quantity = productIdQuantity.get(productId);
            totalCapacity = totalCapacity + quantity;
        }

        Map<Integer, Warehouse> listOfWarehouses = warehouseService.listOfWarehouses;

        for (int warehouseId : listOfWarehouses.keySet()) {
            Map<Warehouse, List<Product>> warehouses = seller.getProductsInWarehouse();
            if (warehouses.containsKey(warehouseId)) {
                Warehouse warehouse = listOfWarehouses.get(warehouseId);
                Map<Date, Integer> dateCapacity = warehouse.getDateCapacity();
                Map<Date, Integer> sortedMap = new TreeMap<>(dateCapacity);

                for (Date date : sortedMap.keySet()) {
                    if (date.compareTo(startDate) >= 0 && sortedMap.get(date) >= totalCapacity) {
                        warehouseDateMap.put(warehouseId, date);
                    }
                }
            }
        }

        return warehouseDateMap;
    }

    public void reserveSlot(int sellerId, Map<Integer, Integer> productIdQuantity, Date date, int warehouseId) {
        int totalCapacity = 0;
        Seller seller = mapOfSellers.get(sellerId);
        Map<Integer, Warehouse> listOfWarehouses = warehouseService.listOfWarehouses;
        Warehouse warehouse = listOfWarehouses.get(warehouseId);
        Map<Warehouse, List<Product>> productsInWarehouse = seller.getProductsInWarehouse();
        List<Product> products = new ArrayList<>();

        for (int productId : productIdQuantity.keySet()) {
            int quantity = productIdQuantity.get(productId);
            totalCapacity = totalCapacity + quantity;

            Map<Integer, Product> listOfProducts = productService.listOfProducts;
            products.add(listOfProducts.get(productId));
            Map<Product, Integer> productQuantity = seller.getProductQuantity();
            int eachProdQuantity = productQuantity.getOrDefault(listOfProducts.get(productId), 0);
            productQuantity.put(listOfProducts.get(productId), eachProdQuantity);
        }

        productsInWarehouse.put(warehouse, products);

        Map<Integer, Date> warehouseDateMap = getSlots(sellerId, productIdQuantity, date);
        for (int id : warehouseDateMap.keySet()) {
            Date dateAvailable = warehouseDateMap.get(id);
            if (dateAvailable == date && id == warehouseId) {
                Map<Date, Integer> dateCapacity = warehouse.getDateCapacity();
                int maxCap = dateCapacity.get(date);
                dateCapacity.put(date, maxCap - totalCapacity);
                int cost = (totalCapacity*10) - (10*seller.getRating());
                int timesOf150 = totalCapacity/150;
                if (timesOf150 > 0 && seller.getRating() < 10) {
                    int rating = seller.getRating()+(1*timesOf150);
                    if (rating < 10)
                        seller.setRating(rating);
                    else
                        seller.setRating(10);
                }
            }
        }
    }

    public void getDetails(int sellerId) {
        Seller seller = mapOfSellers.get(sellerId);
        Map<Product, Integer> productQuantity = seller.getProductQuantity();
        System.out.println("Seller with id : " + sellerId + " has rating of : " + seller.getRating());
        Map<Warehouse, List<Product>> productsInWarehouse = seller.getProductsInWarehouse();
        System.out.println("and onboarded warehouses are : ");
        for (Warehouse warehouse : productsInWarehouse.keySet()) {
            System.out.println(warehouse.getWarehouseId());
            System.out.println(" and this warehouse has following products with their quantities ");
            List<Product> products = productsInWarehouse.get(warehouse);
            for (Product product : products) {
                int quantity = productQuantity.get(product);
                System.out.println(product.getId() + " " + quantity);
            }
        }
    }
}
