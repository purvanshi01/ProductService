package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    // of this method is returning a null we have the chances of getting null pointer exception
    // to avoid that we are using Optional

    Optional<Product> findByTitleAndDescription(String Title, String Description);
    List<Product> findByTitleContaining(String word);
    // using like operator

    Optional<Product> findTopThreeByTitle(String title); // limit the result by 3

    void deleteById(Long id);

    void deleteByTitle(String title);

    Product save(Product product);
}
