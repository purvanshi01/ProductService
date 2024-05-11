package com.example.productservice;

import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.repositories.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testQueries() {
        List<ProductWithIdAndTitle> products = productRepository.someQuery();
        // When we select just id and title from the product and do the below query
        // List<Product> products = productRepository.someQuery();
        // then it will not work
        // because only id and title are not enough to convert into product
        // so here comes the concept of projections
        // Projections are typically used when we are not fetching the complete product or entity object
        // form the db
        System.out.println(products.get(0).getId());
        System.out.println(products.get(0).getTitle());

        // this is executing our sql query
        Product product1 = productRepository.doSomethingSQL();

        // this is executing hibernate query and here it will do join and execute just one single call
        Optional<Product> product2 = productRepository.findById(1L);
        Product product3 = null;
        if(product2.isPresent()) {
            product3 = product2.get();
        }

        System.out.println("DEBUG");
    }

}
