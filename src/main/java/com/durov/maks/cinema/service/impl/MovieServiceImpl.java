package com.durov.maks.cinema.service.impl;

import com.durov.maks.cinema.dao.MovieDao;
import com.durov.maks.cinema.model.Movie;
import com.durov.maks.cinema.service.MovieService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Movie getMovieById(Long movieId) {
        return movieDao.getById(movieId);
    }
}
