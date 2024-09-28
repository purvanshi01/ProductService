package com.example.concertBookingSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConcertTicketBookingSystemDemo {
    public static void main(String[] args) {
        ConcertTicketBookingSystem concertTicketBookingSystem = ConcertTicketBookingSystem.getInstance();

        List<Seat> concert1Seats = generateSeats(100);
        Concert concert1 = new Concert("C001", "Artist 1", "Venue 1", LocalDateTime.now().plusDays(30), concert1Seats);
        concertTicketBookingSystem.addConcert(concert1);

        List<Seat> concert2Seats = generateSeats(50);
        Concert concert2 = new Concert("C002", "Artist 2", "Venue 2", LocalDateTime.now().plusDays(60), concert2Seats);
        concertTicketBookingSystem.addConcert(concert2);

        // Create users
        Users user1 = new Users("U001",  "john@example.com", "John Doe");
        Users user2 = new Users("U002", "jane@example.com", "Jane Smith");

        // Search concerts
        List<Concert> searchResults = concertTicketBookingSystem.searchConcerts("Artist 1", "Venue 1", LocalDateTime.now().plusDays(30));
        System.out.println("Search Results:");
        for (Concert concert : searchResults) {
            System.out.println("Concert: " + concert.getArtist() + " at " + concert.getVenue());
        }


        // Book tickets
        try {
            List<Seat> selectedSeats1 = selectSeats(concert1, 3);
            Booking booking1 = concertTicketBookingSystem.bookTickets(user1, concert1, selectedSeats1);
            List<Seat> selectedSeats2 = selectSeats(concert2, 2);
            Booking booking2 = concertTicketBookingSystem.bookTickets(user2, concert2, selectedSeats2);

            // Cancel booking
            concertTicketBookingSystem.cancelBooking(booking1.getId());

            // Book tickets again
            List<Seat> selectedSeats3 = selectSeats(concert1, 2);
            Booking booking3 = concertTicketBookingSystem.bookTickets(user2, concert1, selectedSeats3);

        } catch (SeatNotAvailableException e) {
            throw new RuntimeException(e);
        }


    }

    private static List<Seat> generateSeats(int numberOfSeats) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            String seatNumber = "S" + i;
            SeatType seatType = (i <= 10) ? SeatType.Vip : (i <= 30) ? SeatType.Premium : SeatType.Regular;
            double price = (seatType == SeatType.Vip) ? 100.0 : (seatType == SeatType.Premium) ? 75.0 : 50.0;
            seats.add(new Seat(seatNumber, seatNumber, seatType, price));
        }
        return seats;
    }

    private static List<Seat> selectSeats(Concert concert, int numberOfSeats) {
        List<Seat> selectedSeats = new ArrayList<>();
        List<Seat> availableSeats = concert.getSeats().stream()
                .filter(seat -> seat.getSeatStatus() == SeatStatus.Available)
                .limit(numberOfSeats)
                .toList();
        selectedSeats.addAll(availableSeats);
        return selectedSeats;
    }
}
