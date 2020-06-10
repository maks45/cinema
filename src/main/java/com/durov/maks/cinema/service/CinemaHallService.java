package com.durov.maks.cinema.service;

import com.durov.maks.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall getCinemaHallById(Long cinemaHallId);
}
