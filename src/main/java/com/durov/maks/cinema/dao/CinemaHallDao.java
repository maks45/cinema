package com.durov.maks.cinema.dao;

import com.durov.maks.cinema.model.CinemaHall;
import java.util.List;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall getById(Long cinemaHallId);
}
