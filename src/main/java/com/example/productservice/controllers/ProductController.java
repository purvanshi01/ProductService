package com.example.productservice.controllers;

import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.exceptions.InvalidProductIdException;
import com.example.productservice.exceptions.ProductControllerSpecificExceptions;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    ProductController (@Qualifier("selfProductServiceePurvi") ProductService productService) {
        this.productService = productService;
    }

    // localhost:8080/products/10
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {
        /* Product product = null;
        try {
            product = productService.getProductById(id);
        } catch (RuntimeException e) {
            System.out.println("Something went wrong");
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }*/

        // It should not be controllers responsibility to handle exceptions
        // Controller should just take request and give response
        // The above should be done elsewhere.
        // Controller Advice
        // int a = 2/0;
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // localhost:8080/products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
        // return new Product();
    }

    //  Partial update
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Replace a product
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody ProductRequestDto productRequestDto) {
        return productService.replaceProduct(id, productRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {

    }

    /* This exception handler will only be trigger if this type of exception is thrown only from this controller
    Lets say if there is order controller which is throwing this exception as well
    this method will not be triggered
    this is how you can define controller specific exception handlers
    */
    @ExceptionHandler(ProductControllerSpecificExceptions.class)
    public ResponseEntity<Void> handleProductControllerSpecificException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
