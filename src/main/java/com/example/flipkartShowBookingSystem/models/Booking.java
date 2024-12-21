package com.example.flipkartShowBookingSystem.models;

public class Booking {
    private String bookingId;
    private Show show;
    private User user;
    private int numberOfSeats;
    private int amount;

    public Booking(String bookingId, Show show, User user, int numberOfSeats) {
        this.bookingId = bookingId;
        this.show = show;
        this.user = user;
        this.numberOfSeats = numberOfSeats;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
