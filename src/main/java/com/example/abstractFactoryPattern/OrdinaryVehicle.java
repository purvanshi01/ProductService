package com.example.abstractFactoryPattern;

public class OrdinaryVehicle implements VehicleFactory {
    @Override
    public Vehicle getVehicle(String input) {
        switch (input) {
            case "Swift":
                return new Swift();
            case "Alto":
                return new Alto();
            default:
                return null;
        }
    }
}
