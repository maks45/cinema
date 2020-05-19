package com.durov.maks.cinema.dao;

import java.util.List;
import com.durov.maks.cinema.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
