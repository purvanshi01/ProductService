package com.example.flipkartShowBookingSystem.service;

import com.example.flipkartShowBookingSystem.models.Genre;
import com.example.flipkartShowBookingSystem.models.Show;

import java.util.List;

public interface IShowRankingStrategy {

    List<Show> showsAvailable();
}
