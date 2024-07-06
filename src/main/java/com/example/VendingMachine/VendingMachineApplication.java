package com.example.VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineApplication {
    public static void main(String args[]) {
        VendingMachine vendingMachine = VendingMachine.getInstance();

        vendingMachine.addProducts("Kit kat", 5, 10);
        vendingMachine.addProducts("Dairy Milk", 6, 20);
        vendingMachine.addProducts("Coke", 2, 40);
        vendingMachine.addProducts("Burger", 1, 100);
        vendingMachine.addProducts("Lays", 8, 20);

        try {
            vendingMachine.selectProduct("Burger", 2);
            int price = vendingMachine.selectProduct("Burger", 1);
            System.out.println("Price for 1 burger is " + price);
            Map<Currency, Integer> quantityToCurrencyMap = new HashMap<>();
            vendingMachine.dispenseProductAndPay("Burger", 2, quantityToCurrencyMap);
            Currency fiveCoin = new Currency(CurrencyType.COINS, 5);
            Currency oneCoin = new Currency(CurrencyType.COINS, 1);
            quantityToCurrencyMap.put(fiveCoin, 10);
            quantityToCurrencyMap.put(new Currency(CurrencyType.NOTES, 20), 5);
            quantityToCurrencyMap.put(oneCoin, 100);
            vendingMachine.dispenseProductAndPay("Burger", 1, quantityToCurrencyMap);

            quantityToCurrencyMap.remove(fiveCoin);
            quantityToCurrencyMap.remove(oneCoin);
            vendingMachine.dispenseProductAndPay("Burger", 1, quantityToCurrencyMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


// inventory take and make it concurrent
// insert coin insert note, one at a time, no need to maintain incoming amount
// assume that the money is infinite to give change and just do difference and return
// use concurrent hashmaps
// payment service should be different
// exceptions handle in main class
// naming like lookup etc