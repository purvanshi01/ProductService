package com.example.abstractFactoryPattern;

public class AbstractVehicleFactory {
    public VehicleFactory getVehicleFactory(String input) {
        switch (input) {
            case "LuxuryVehicle":
                return new LuxuryVehicle();
            case "OrdinaryVehicle":
                return new OrdinaryVehicle();
            default:
                return null;
        }
    }
}
