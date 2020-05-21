package com.durov.maks.cinema;

import com.durov.maks.cinema.lib.Injector;
import com.durov.maks.cinema.model.CinemaHall;
import com.durov.maks.cinema.model.Movie;
import com.durov.maks.cinema.model.MovieSession;
import com.durov.maks.cinema.service.CinemaHallService;
import com.durov.maks.cinema.service.MovieService;
import com.durov.maks.cinema.service.MovieSessionService;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.durov.maks.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) INJECTOR.getInstance(MovieService.class);
        movieService.add(movie);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("100 human cinema hall");
        CinemaHallService cinemaHallService =
                (CinemaHallService) INJECTOR.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.now());
        MovieSessionService movieSessionService =
                (MovieSessionService) INJECTOR.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        List<Movie> movies = movieService.getAll();
        List<MovieSession> availableSessions = movieSessionService
                .findAvailableSessions(movie.getId(), LocalDateTime.now().toLocalDate());
        List<CinemaHall> cinemaHalls = cinemaHallService.getAll();
        availableSessions.forEach(System.out::println);
        movies.forEach(System.out::println);
        cinemaHalls.forEach(System.out::println);
    }
}
