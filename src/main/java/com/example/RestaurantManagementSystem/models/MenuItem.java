package com.example.RestaurantManagementSystem.models;

public class MenuItem {
    String name;
    int price;
    VegNonVeg vegNonVeg;
    CategoryType categoryType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public VegNonVeg getVegNonVeg() {
        return vegNonVeg;
    }

    public void setVegNonVeg(VegNonVeg vegNonVeg) {
        this.vegNonVeg = vegNonVeg;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }
}
