package com.durov.maks.cinema.service;

import com.durov.maks.cinema.model.Movie;
import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    Movie getMovieById(Long id);

    Movie add(Movie movie);
}
