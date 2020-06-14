package com.durov.maks.cinema.model.dto.moviesession;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MovieSessionRequestDto {
    @NotNull(message = "date and time can't be null")
    private LocalDateTime date;
    @NotNull(message = "movie id can't be null")
    private Long movieId;
    @NotNull(message = "cinema hall id can't be null")
    private Long cinemaHallId;

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
