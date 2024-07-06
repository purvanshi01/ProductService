package com.example.abstractFactoryPattern;

public class BMW implements Vehicle {
    @Override
    public void drive() {
        System.out.println("You are driving a BMW");
    }
}
