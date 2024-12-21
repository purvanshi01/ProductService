package com.example.flipkartShowBookingSystem.service;

import com.example.flipkartShowBookingSystem.models.Genre;
import com.example.flipkartShowBookingSystem.models.Show;

import java.util.Comparator;
import java.util.List;

public class StartTimeBasedShowRankingStrategy implements IShowRankingStrategy {
    private final ShowService showService;
    private Genre genre;

    public StartTimeBasedShowRankingStrategy(ShowService showService) {
        this.showService = showService;
    }

    @Override
    public List<Show> showsAvailable() {
        List<Show> availableShows = showService.showLookUpByGenre.get(genre);
        if (!availableShows.isEmpty()) {
            availableShows.sort(Comparator.comparing(Show::getStartTime));
        }
        return availableShows;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
