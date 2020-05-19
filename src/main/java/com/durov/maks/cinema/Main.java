package com.durov.maks.cinema;

import com.durov.maks.cinema.lib.Injector;
import com.durov.maks.cinema.model.Movie;
import com.durov.maks.cinema.service.MovieService;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.durov.maks.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
