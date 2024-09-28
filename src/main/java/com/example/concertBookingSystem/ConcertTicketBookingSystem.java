package com.example.concertBookingSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcertTicketBookingSystem {
    public static ConcertTicketBookingSystem instance;
    private final Map<String, Concert> concerts;
    private final Map<String, Booking> bookings;
    private final Object lock = new Object();

    private ConcertTicketBookingSystem() {
        this.concerts = new ConcurrentHashMap<>();
        this.bookings = new ConcurrentHashMap<>();
    }

    public static synchronized ConcertTicketBookingSystem getInstance() {
        if (instance == null) {
            instance = new ConcertTicketBookingSystem();
        }
        return instance;
    }

    public void addConcert(Concert concert) {
        concerts.put(concert.getId(), concert);
    }

    public Concert getConcert(String concertId) {
        return concerts.get(concertId);
    }

    public List<Concert> searchConcerts(String artist, String venue, LocalDateTime dateTime) {
        List<Concert> filteredConcerts = new ArrayList<>();
        for (String concertId : concerts.keySet()) {
            Concert concert = concerts.get(concertId);
            if (concert.getArtist().equalsIgnoreCase(artist) && concert.getVenue().equalsIgnoreCase(venue)
                && concert.getDateTime().equals(dateTime)) {
                filteredConcerts.add(concert);
            }
        }

        return filteredConcerts;
    }

    public Booking bookTickets(Users user, Concert concert, List<Seat> seats) throws SeatNotAvailableException {
        synchronized (lock) {
            for (Seat seat : seats) {
                if (seat.getSeatStatus() != SeatStatus.Available) {
                    throw new SeatNotAvailableException("Seat " + seat.getSeatNumber() + " is not available");
                }
            }

            for (Seat seat : seats) {
                seat.bookSeat();
            }


            Booking booking = new Booking("BKG" + UUID.randomUUID(), user, concert, seats);
            bookings.put(booking.getId(), booking);

            // process payment
            processPayment(booking);

            // confirm booking
            booking.confirmBooking();

            System.out.println("Booking " + booking.getId() + " - " + booking.getSeats().size() + " seats booked");

            return booking;
        }
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.cancelBooking();
            bookings.remove(bookingId);
        }
    }

    private void processPayment(Booking booking) {
        // Process payment for the booking
        // ...
    }
}
