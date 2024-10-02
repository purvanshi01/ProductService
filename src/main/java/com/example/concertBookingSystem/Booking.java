package com.example.concertBookingSystem;

import java.util.List;

/* Purvi*/

public class Booking {
    private final String id;
    private final Users user;
    private final Concert concert;
    private final List<Seat> seats;
    private final double totalPrice;
    private BookingStatus bookingStatus;

    public Booking(String id, Users user, Concert concert, List<Seat> seats) {
        this.id = id;
        this.user = user;
        this.concert = concert;
        this.seats = seats;
        this.totalPrice = calculateTotalPrice();
        this.bookingStatus = BookingStatus.Pending;
    }

    public String getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public Concert getConcert() {
        return concert;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    private double calculateTotalPrice() {
        double total = 0;
        for (Seat seat : seats) {
            total = total + seat.getPrice();
        }
        return total;
    }

    public void confirmBooking() {
        if (bookingStatus == BookingStatus.Pending) {
            bookingStatus = BookingStatus.Confirmed;
            // Send booking confirmation to the user
            // ...
        }
    }

    public void cancelBooking() {
        if (bookingStatus == BookingStatus.Confirmed) {
            bookingStatus = BookingStatus.Cancelled;
            seats.forEach(Seat::releaseSeat);
            System.out.printf("Booking %s cancelled\n", id);
            // Send booking cancellation notification to the user
            // ...
        }
    }
}
