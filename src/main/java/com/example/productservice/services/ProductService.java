package com.example.productservice.services;

import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById (Long id) throws InvalidProductIdException;
    List<Product> getAllProducts();

    Product updateProduct();

    /* the input is something like category is also a string and it does not directly can be converted to
     * product so either we accept the parameter as FakeStoreProductDto which is not right because
     * tomorrow the input might be RealStoreProductDto so we should not have this input in the interface
     * instead create a class called ProductRequestDto and take that as input and convert that to FakeStoreProductDto
     * in the FakeStoreProductService class*/
    Product replaceProduct(Long id, ProductRequestDto productRequestDto);

    Product createProduct(Product product);

    void deleteProduct();
}
