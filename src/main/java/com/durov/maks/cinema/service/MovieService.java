package com.durov.maks.cinema.service;

import java.util.List;
import com.durov.maks.cinema.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
