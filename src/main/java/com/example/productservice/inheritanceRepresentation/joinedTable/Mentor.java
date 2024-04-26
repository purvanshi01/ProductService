package com.example.productservice.inheritanceRepresentation.joinedTable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_mentor")
public class Mentor extends User {
    private double avgRating;
}
