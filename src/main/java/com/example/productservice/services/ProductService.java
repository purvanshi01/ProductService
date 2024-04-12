package com.example.productservice.services;

import com.example.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById (Long id);
    List<Product> getAllProducts();

    Product updateProduct();

    Product replaceProduct();

    Product createProduct();

    void deleteProduct();
}
