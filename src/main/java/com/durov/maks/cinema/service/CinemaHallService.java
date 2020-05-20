package com.durov.maks.cinema.service;

import java.util.List;
import com.durov.maks.cinema.model.CinemaHall;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
