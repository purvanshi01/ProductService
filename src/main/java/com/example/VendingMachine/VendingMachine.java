package com.example.VendingMachine;

import com.example.VendingMachine.exceptions.ProductNotFoundException;

import java.util.*;

public class VendingMachine {
    Map<Product, Integer> quantityOfAvailableProducts;
    Map<String, Product> productNameToProductObjectMap;

    Map<Currency, Integer> availableQuantityToCurrencyMap;

    private static VendingMachine instance;

    VendingMachine() {
        quantityOfAvailableProducts = new HashMap<>();
        productNameToProductObjectMap = new HashMap<>();
        availableQuantityToCurrencyMap = new HashMap<>();
    }

    public static VendingMachine getInstance() {
        if (instance == null) {
            instance = new VendingMachine();
        }
        return instance;
    }

    public int selectProduct(String name, int quantity) throws  Exception {
        int price = 0;
        Product product;
        if (!productNameToProductObjectMap.containsKey(name)) {
            throw new ProductNotFoundException(400, "This Product does not exist in the vending machine");
        }

        product = productNameToProductObjectMap.get(name);
        int availableQuantity = quantityOfAvailableProducts.get(product);
        if (availableQuantity == 0) {
            // throw product out of stock exception
            System.out.println("throw product out of stock exception");
        }

        if (availableQuantity < quantity) {
            System.out.println("throw product less than required exception, available is " + availableQuantity);
        }

        price = price + product.getPrice() * quantity;
        return price;
    }

    public Map<Currency, Integer> dispenseProductAndPay(String name, int productQuantity, Map<Currency, Integer> quantityToCurrencyMap) throws Exception {
        int price = selectProduct(name, productQuantity);
        Product product = productNameToProductObjectMap.get(name);

        int amount = 0;
        Map<Currency, Integer> change = new HashMap<>();

        for (Currency currency : quantityToCurrencyMap.keySet()) {
            amount = amount + currency.getDenomination() * quantityToCurrencyMap.get(currency);
        }
        if (amount < price) {
            // throw exception
            System.out.println("insufficient amount, please assert money greater than equal to " + price);
        } else if (amount > price) {
            int differenceAmount = amount - price;

            List<Map.Entry<Currency, Integer> > list =
                    new LinkedList<Map.Entry<Currency, Integer> >(availableQuantityToCurrencyMap.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<Currency, Integer> >() {
                public int compare(Map.Entry<Currency, Integer> o1,
                                   Map.Entry<Currency, Integer> o2)
                {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            });

            for (Currency currency : availableQuantityToCurrencyMap.keySet()) {
                int denomination = currency.getDenomination();
                int availableQuantity = availableQuantityToCurrencyMap.get(currency);
                if (denomination > differenceAmount)
                    continue;
                int quantityOfThisDenomination = Math.floorDiv(differenceAmount, denomination);
                if (quantityOfThisDenomination > availableQuantity) {
                    differenceAmount = differenceAmount - availableQuantity * denomination;
                    availableQuantityToCurrencyMap.put(currency, 0);
                    Currency changeCurrency = new Currency(currency.getCurrencyType(), denomination);
                    change.put(changeCurrency, availableQuantity);
                } else {
                    Currency changeCurrency = new Currency(currency.getCurrencyType(), denomination);
                    change.put(changeCurrency, quantityOfThisDenomination);
                    differenceAmount = differenceAmount - quantityOfThisDenomination * denomination;
                    availableQuantityToCurrencyMap.put(currency, availableQuantity - quantityOfThisDenomination * denomination);
                }

                if (differenceAmount == 0) {
                    storeReceivedCurrencyQuantityInVendingMachine(quantityToCurrencyMap, price);
                    quantityOfAvailableProducts.put(product, quantityOfAvailableProducts.get(product) - productQuantity);
                    return change;
                }
            }

            if (differenceAmount != 0) {
                // throw exception
                System.out.println("We do not have sufficient change for please take the money back");
            } else {
                quantityOfAvailableProducts.put(product, quantityOfAvailableProducts.get(product) - productQuantity);
                storeReceivedCurrencyQuantityInVendingMachine(quantityToCurrencyMap, price);
            }
        } else {
            // amount == price
            quantityOfAvailableProducts.put(product, quantityOfAvailableProducts.get(product) - productQuantity);
            storeReceivedCurrencyQuantityInVendingMachine(quantityToCurrencyMap, price);
        }

        return change;
    }

    public void addProducts(String name, int quantity, int price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productNameToProductObjectMap.put(name, product);
        quantityOfAvailableProducts.put(product, quantity);
    }

    private void storeReceivedCurrencyQuantityInVendingMachine(Map<Currency, Integer> quantityToCurrencyMap, int price) {
        for (Currency currency : quantityToCurrencyMap.keySet()) {
            int quantity = quantityToCurrencyMap.get(currency);
            if (availableQuantityToCurrencyMap.containsKey(currency)) {
                quantity = quantity + availableQuantityToCurrencyMap.get(currency);
            }
            availableQuantityToCurrencyMap.put(currency, quantity);
        }
    }
}
