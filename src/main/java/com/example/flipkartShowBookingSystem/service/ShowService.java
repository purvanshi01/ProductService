package com.example.flipkartShowBookingSystem.service;

import com.example.flipkartShowBookingSystem.exceptions.ShowRegistrationFailedException;
import com.example.flipkartShowBookingSystem.exceptions.ShowSlotTimeExceededException;
import com.example.flipkartShowBookingSystem.models.Genre;
import com.example.flipkartShowBookingSystem.models.Show;
import com.example.flipkartShowBookingSystem.models.User;
import com.example.flipkartShowBookingSystem.models.UserType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowService {
    Map<Genre, List<Show>> showLookUpByGenre;
    Map<String, List<Show>> showSlotsLookupByName;
    public static ShowService instance;

    public static ShowService getInstance() {
        if (instance == null) {
            instance = new ShowService();
        }
        return instance;
    }

    private ShowService() {
        showLookUpByGenre = new HashMap<>();
        showSlotsLookupByName = new HashMap<>();
    }

    public boolean registerShow(Show show, Genre genre, User user) throws ShowRegistrationFailedException {
        if (user.getUserType() == UserType.ORGANISER) {
            if (!showLookUpByGenre.containsKey(genre)) {
                showLookUpByGenre.put(genre, new ArrayList<Show>());
            }
            showLookUpByGenre.get(genre).add(show);
            showSlotsLookupByName.put(show.getShowName(), new ArrayList<>());
        } else {
            throw new ShowRegistrationFailedException("Only organisers can register shows");
        }
        return true;
    }

    public void onboardShowSlots(List<Show> shows) throws ShowSlotTimeExceededException {
        for (Show show : shows) {
            Duration duration = Duration.between(show.getStartTime(), show.getEndTime());
            if (duration.toHours() > 1) {
                throw new ShowSlotTimeExceededException("Sorry, show timings are of 1 hour only");
            }
            showSlotsLookupByName.get(show.getShowName()).add(show);
        }
    }
}
