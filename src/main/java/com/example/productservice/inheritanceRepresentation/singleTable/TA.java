package com.example.productservice.inheritanceRepresentation.singleTable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_ta")
public class TA extends User {
    private int numberOfSessions;
    private double avgRating;
}
