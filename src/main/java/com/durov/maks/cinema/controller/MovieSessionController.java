package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.model.MovieSession;
import com.durov.maks.cinema.model.dto.moviesession.MovieSessionRequestDto;
import com.durov.maks.cinema.model.dto.moviesession.MovieSessionResponseDto;
import com.durov.maks.cinema.service.CinemaHallService;
import com.durov.maks.cinema.service.MovieService;
import com.durov.maks.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieService movieService,
                                  CinemaHallService cinemaHallService) {
        this.movieSessionService = movieSessionService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(getMovieSession(movieSessionRequestDto));
    }

    @GetMapping
    public List<MovieSessionResponseDto> getAllMovieSessions(
            @RequestParam Long movieId, @RequestParam ("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(this::getMovieSessionResponseDto)
                .collect(Collectors.toList());
    }

    private MovieSession getMovieSession(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(movieSessionRequestDto.getDate());
        movieSession.setMovie(movieService.getMovieById(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.getCinemaHallById(movieSessionRequestDto
                .getCinemaHallId()));
        return movieSession;
    }

    private MovieSessionResponseDto getMovieSessionResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieSessionId(movieSession.getId());
        movieSessionResponseDto.setDate(movieSession.getShowTime());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        return movieSessionResponseDto;
    }
}
