package com.example.productservice.controllers;

import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    // should we create the object of servie manually like below?
    // no right we should be injecting the dependency
    // private ProductService productService = new ProductService();

    private ProductService productService;

    // here we are injecting the dependency but before that we should have the object of it.
    // There should be a bean of ProductService
    // you should ask spring to create object of it
    // therefore you have to give @service annotation on ProductService
    // when you do @service annotation so when the spring application will start
    // spring will create the objects of all the classes that you have asked spring to create
    ProductController (ProductService productService) {
        this.productService = productService;
    }

    // localhost:8080/products/10
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    // localhost:8080/products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return new Product();
    }

    //  Partial update
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new Product();
    }

    // Replace a product
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {

    }
}
