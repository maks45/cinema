package com.durov.maks.cinema.service;

import com.durov.maks.cinema.model.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
