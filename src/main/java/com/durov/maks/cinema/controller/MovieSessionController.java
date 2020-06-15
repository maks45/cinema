package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.model.dto.moviesession.MovieSessionRequestDto;
import com.durov.maks.cinema.model.dto.moviesession.MovieSessionResponseDto;
import com.durov.maks.cinema.model.mapper.MovieSessionMapper;
import com.durov.maks.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionMapper movieSessionMapper;
    private final MovieSessionService movieSessionService;

    public MovieSessionController(MovieSessionMapper movieSessionMapper,
                                  MovieSessionService movieSessionService) {
        this.movieSessionMapper = movieSessionMapper;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping
    public void addMovieSession(
            @RequestBody @Valid MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper.getMovieSession(movieSessionRequestDto));
    }

    @GetMapping
    public List<MovieSessionResponseDto> getAllMovieSessions(
            @RequestParam Long movieId, @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date).stream()
                .map(movieSessionMapper::getMovieSessionResponseDto)
                .collect(Collectors.toList());
    }
}
