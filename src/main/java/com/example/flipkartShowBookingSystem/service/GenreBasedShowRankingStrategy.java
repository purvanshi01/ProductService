package com.example.flipkartShowBookingSystem.service;

import com.example.flipkartShowBookingSystem.models.Genre;
import com.example.flipkartShowBookingSystem.models.Show;

import java.util.ArrayList;
import java.util.List;

public class GenreBasedShowRankingStrategy implements IShowRankingStrategy {
    private final ShowService showService;
    private Genre genre;

    public GenreBasedShowRankingStrategy(ShowService showService) {
        this.showService = showService;
    }

    @Override
    public List<Show> showsAvailable() {
        List<Show> availableShows = showService.showLookUpByGenre.get(this.genre);
        return availableShows;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
