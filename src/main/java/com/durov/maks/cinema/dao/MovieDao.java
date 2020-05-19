package com.durov.maks.cinema.dao;

import com.durov.maks.cinema.model.Movie;
import java.util.List;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
