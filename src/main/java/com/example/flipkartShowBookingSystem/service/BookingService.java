package com.example.flipkartShowBookingSystem.service;

import com.example.flipkartShowBookingSystem.exceptions.NumberOfSeatesGreaterThanAvailableCapacityException;
import com.example.flipkartShowBookingSystem.exceptions.UserCannotBookTicketsForAlreadyBookedTimeSlotForDifferentShow;
import com.example.flipkartShowBookingSystem.models.Booking;
import com.example.flipkartShowBookingSystem.models.Show;
import com.example.flipkartShowBookingSystem.models.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class BookingService {
    private static BookingService instance;
    Map<String, Booking> bookingLookUpById;
    Map<User, List<Booking>> userBookings;

    public BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    private BookingService() {
        bookingLookUpById = new HashMap<>();
        userBookings = new HashMap<>();
    }

    public String bookTicket(User user, Show show, int numberOfSeats) throws NumberOfSeatesGreaterThanAvailableCapacityException, UserCannotBookTicketsForAlreadyBookedTimeSlotForDifferentShow {
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, show, user, numberOfSeats);

        if (show.getCapacity() == 0) {
            Queue<User> waitlist = show.getWaitingUsers();
            waitlist.add(user);
            throw new NumberOfSeatesGreaterThanAvailableCapacityException("You have been waitlisted!!");
        }
        if (!userBookings.containsKey(user)) {
            if (show.getCapacity() - numberOfSeats >= 0) {
                show.setCapacity(show.getCapacity() - numberOfSeats);
                userBookings.put(user, new ArrayList<>());
                userBookings.get(user).add(booking);
            } else {
                throw new NumberOfSeatesGreaterThanAvailableCapacityException("Number of seats exceedsed the available capacity");
            }
        } else {
            List<Booking> bookings = userBookings.get(user);

            for (Booking existingBooking : bookings) {
                Show existingShow = existingBooking.getShow();
                LocalDateTime existingBookingEndTime = existingBooking.getShow().getEndTime();
                LocalDateTime currentBookingStartTime = show.getStartTime();

                if (currentBookingStartTime.isAfter(existingBookingEndTime) || existingShow.getShowName().equals(show.getShowName())) {
                    if (show.getCapacity() - numberOfSeats >= 0) {
                        show.setCapacity(show.getCapacity() - numberOfSeats);
                        userBookings.put(user, new ArrayList<>());
                        userBookings.get(user).add(booking);
                    } else {
                        throw new NumberOfSeatesGreaterThanAvailableCapacityException("Number of seats exceeded the available capacity");
                    }
                } else {
                    throw new UserCannotBookTicketsForAlreadyBookedTimeSlotForDifferentShow("User Cannot Book Tickets For Already Booked Time Slot For Different Show");
                }
            }
        }
        bookingLookUpById.put(bookingId, booking);
        return bookingId;
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookingLookUpById.get(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found for ID: " + bookingId);
        }

        Show show = booking.getShow();
        show.setCapacity(booking.getShow().getCapacity() + booking.getNumberOfSeats());
        List<Booking> bookings = userBookings.get(booking.getUser());
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getBookingId().equals(bookingId)) {
                bookings.remove(i);
            }
        }
        bookingLookUpById.remove(bookingId);

        // update waitlist
        Queue<User> waitlist = show.getWaitingUsers();
        User user = waitlist.poll();
        System.out.println("Waiting user: " + user + " you can now start booking your tickets, the available" +
                "capacity of the show " + show.getShowName() + " is " + show.getCapacity());
    }
}
