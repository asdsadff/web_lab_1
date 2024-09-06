package com.strutinsky.model;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Director {
    private long id;
    private String firstName;
    private String lastName;
    private String biography;
    private LocalDate birthDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(firstName, director.firstName) && Objects.equals(lastName, director.lastName) && Objects.equals(birthDate, director.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate);
    }
}
