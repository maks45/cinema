package com.durov.maks.cinema.dao;

import java.util.List;
import com.durov.maks.cinema.model.CinemaHall;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
