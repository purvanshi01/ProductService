package com.example.RestaurantManagementSystem;

import com.example.RestaurantManagementSystem.models.*;
import com.example.RestaurantManagementSystem.service.AdminService;
import com.example.RestaurantManagementSystem.service.CustomerService;
import com.example.RestaurantManagementSystem.service.OrderService;

public class RestaurantManagementSystemApplication {
    public static void main(String args[]) {
        User user1 = new User();
        user1.addUser("123", "naman");


        AdminService adminService = AdminService.getInstance();
        CustomerService customerService = CustomerService.getInstance();
        OrderService orderService = OrderService.getInstance();


        adminService.addTable(4);
        adminService.addTable(3);

        adminService.addMenuItems("French Fries", 100, VegNonVeg.VEG, CategoryType.STARTER);
        adminService.addMenuItems("Aloo tikiya", 200, VegNonVeg.VEG, CategoryType.STARTER);
        adminService.addMenuItems("chole chawal", 400, VegNonVeg.VEG, CategoryType.MAIN_COURSE);
        adminService.addMenuItems("ice cream", 100, VegNonVeg.VEG, CategoryType.DESSERT);
        adminService.addMenuItems("Chicken wings", 500, VegNonVeg.NON_VEG, CategoryType.STARTER);

        adminService.removeMenuItems("ice cream");
        adminService.updateMenuItemPrice(600, "Chicken wings");

        customerService.browseMenus(VegNonVeg.NON_VEG);
        customerService.reserveTable(1, "123");
        int tableNumber = customerService.allocateTable(3, "123");
        System.out.println("Allocated Table is " + tableNumber);

        orderService.addMenuItemsToOrder(2, 1, "French Fries");
        orderService.addMenuItemsToOrder(2, 1, "Aloo tikiya");
        orderService.addMenuItemsToOrder(2, 2, "chole chawal");
        orderService.removeMenuItemsFromOrder(2, "chole chawal");
        orderService.updateMenuItemQuantityInOrder(2, "French Fries", 2);

        int orderNumber = orderService.placeOrder(2);
        System.out.println("Order number for table 2 is placed " + orderNumber);

        Bill bill = orderService.generateBill(2, PaymentType.CREDIT_CARD);
        System.out.println("Bill for table 2 for user with id  " + bill.getUserId() + " is a total amount of " + bill.getTotalAmount());

        orderService.payBill(2);
    }

}
