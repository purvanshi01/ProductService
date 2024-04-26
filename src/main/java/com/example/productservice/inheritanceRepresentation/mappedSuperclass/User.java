package com.example.productservice.inheritanceRepresentation.mappedSuperclass;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
}
