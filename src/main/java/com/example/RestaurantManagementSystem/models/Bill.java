package com.example.RestaurantManagementSystem.models;

public class Bill {
    int tableNumber;
    int totalAmount;
    int additionalCharges;
    int billId;

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getAdditionalCharges() {
        return additionalCharges;
    }

    public void setAdditionalCharges(int additionalCharges) {
        this.additionalCharges = additionalCharges;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    PaymentType paymentType;
    int userId;
}
