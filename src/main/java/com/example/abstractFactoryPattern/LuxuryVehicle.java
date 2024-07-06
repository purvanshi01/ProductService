package com.example.abstractFactoryPattern;

import com.example.factoryPattern.Circle;
import com.example.factoryPattern.Rectangle;

public class LuxuryVehicle implements VehicleFactory {
    @Override
    public Vehicle getVehicle(String input) {
        switch (input) {
            case "Mercedes":
                return new Mercedes();
            case "BMW":
                return new BMW();
            default:
                return null;
        }
    }
}
