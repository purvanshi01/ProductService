package com.example.flipkartShowBookingSystem.models;

import java.time.LocalDateTime;
import java.util.Queue;

public class Show {
    private String showName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int capacity;
    private int price;
    private Genre genre;
    Queue<User> waitingUsers;

    public Show(String showName, LocalDateTime startTime, int capacity, int price, Genre genre) {
        this.showName = showName;
        this.startTime = startTime;
        this.capacity = capacity;
        this.price = price;
        this.genre = genre;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Queue<User> getWaitingUsers() {
        return waitingUsers;
    }

    public void setWaitingUsers(Queue<User> waitingUsers) {
        this.waitingUsers = waitingUsers;
    }
}
