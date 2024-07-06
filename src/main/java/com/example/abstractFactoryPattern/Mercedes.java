package com.example.abstractFactoryPattern;

public class Mercedes implements Vehicle {
    @Override
    public void drive() {
        System.out.println("You are driving a mercedes");
    }
}
