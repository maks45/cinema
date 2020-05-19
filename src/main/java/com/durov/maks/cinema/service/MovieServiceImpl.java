package com.durov.maks.cinema.service;

import com.durov.maks.cinema.dao.MovieDao;
import com.durov.maks.cinema.lib.Inject;
import com.durov.maks.cinema.lib.Service;
import com.durov.maks.cinema.model.Movie;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
