package com.strutinsky.model;

import lombok.*;

import java.security.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private long id;
    private String title;
    private String genre;
    private LocalDate releaseDate;
    private Duration duration;
    private Long directorId;

}
