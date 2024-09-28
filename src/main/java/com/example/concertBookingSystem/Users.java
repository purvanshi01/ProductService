package com.example.concertBookingSystem;

public class Users {
    private final String id;
    private final String emailId;
    private final String name;

    public Users(String id, String emailId, String name) {
        this.id = id;
        this.emailId = emailId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getName() {
        return name;
    }
}
