package com.durov.maks.cinema.service.impl;

import com.durov.maks.cinema.dao.CinemaHallDao;
import com.durov.maks.cinema.lib.Inject;
import com.durov.maks.cinema.lib.Service;
import com.durov.maks.cinema.model.CinemaHall;
import com.durov.maks.cinema.service.CinemaHallService;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
