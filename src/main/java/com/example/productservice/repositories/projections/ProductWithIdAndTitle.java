package com.example.productservice.repositories.projections;

public interface ProductWithIdAndTitle {

    // simple interface with attributes that you want to fetch from db
    Long getId();
    String getTitle();
}
