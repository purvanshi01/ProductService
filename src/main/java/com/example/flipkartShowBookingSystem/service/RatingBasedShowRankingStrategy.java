package com.example.flipkartShowBookingSystem.service;

import com.example.flipkartShowBookingSystem.models.Genre;
import com.example.flipkartShowBookingSystem.models.Show;

import java.util.List;

public class RatingBasedShowRankingStrategy implements IShowRankingStrategy {
    private final ShowService showService;

    public RatingBasedShowRankingStrategy(ShowService showService) {
        this.showService = showService;
    }
    @Override
    public List<Show> showsAvailable() {
        return List.of();
    }
}
