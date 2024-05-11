package com.example.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    // @Id
    // private long id;
    private String title;
    private double price;
    /*
    this is not a wise decision if you delete a product then you delete the category as well
    because that category might have other products as well
    therefore we should not do this instead if we delete a category then we can delete all the associated
    products.
    so define cascading in category model
    so here lets say we dont want all these operations but we may want that if i save the product
    then the category is automatically save, for that we can use PERSIST
    @ManyToOne(cascade = {CascadeType.ALL})
    private Category category;
    * */

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category; // Eager fetch by default
    private String description;
    private String image;
}
