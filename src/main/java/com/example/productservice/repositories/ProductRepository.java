package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import com.example.productservice.repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    // @Query("Custom Query") // HQL -> Hibernate query langauge
    // Optional<Product> someRandomQuery();

    // this method will return a product with only Id and Title

    /* @Query("select p from Product p where p.id = 1")
    List<Product> someQuery();*/

    @Query("select p.id as id,p.title as title from Product p where p.id = 1")
    List<ProductWithIdAndTitle> someQuery();

    // How many database calls is the below query making -> 2
    // first select the product object and then fetching the category object
    @Query(value = "select * from product p where p.id = 1", nativeQuery = true)
    Product doSomethingSQL();
}
