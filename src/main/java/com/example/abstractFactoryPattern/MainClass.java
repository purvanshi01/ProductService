package com.example.abstractFactoryPattern;

import com.example.factoryPattern.Shape;
import com.example.factoryPattern.ShapeFactory;

public class MainClass {
    public static void main(String args[]) {
        AbstractVehicleFactory abstractVehicleFactory = new AbstractVehicleFactory();
        VehicleFactory vehicleFactory = abstractVehicleFactory.getVehicleFactory("LuxuryVehicle");
        Vehicle vehicle = vehicleFactory.getVehicle("Mercedes");
        vehicle.drive();
    }
}
