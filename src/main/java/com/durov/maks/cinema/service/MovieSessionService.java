package com.durov.maks.cinema.service;

import java.time.LocalDate;
import java.util.List;
import com.durov.maks.cinema.model.MovieSession;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);
}
