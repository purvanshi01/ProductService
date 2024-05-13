package com.example.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    // @Id
    // private long id;
    private String title;

    /* Here i want to say that if i delete a category then delete the products related to that category
    * for that we have to declare the product here like below */

    /*the relation between catrgory and product defined in both the classes is same and
    * for hibernate to know this what we have is a annotation by jpa called mappedBy
    * whatever name of the attribute is there in the product table for category that we will have
    * here, like Category category then we will use here category*/
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products; //  lazy fetch by default

    /*the above lines mean that if we are removing the category then remove all the associated products as well*/
}
