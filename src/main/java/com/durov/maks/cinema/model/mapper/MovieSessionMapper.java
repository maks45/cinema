package com.durov.maks.cinema.model.mapper;

import com.durov.maks.cinema.model.MovieSession;
import com.durov.maks.cinema.model.dto.moviesession.MovieSessionRequestDto;
import com.durov.maks.cinema.model.dto.moviesession.MovieSessionResponseDto;
import com.durov.maks.cinema.service.CinemaHallService;
import com.durov.maks.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    @Autowired
    private MovieService movieService;
    @Autowired
    private CinemaHallService cinemaHallService;

    public MovieSession getMovieSession(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(movieSessionRequestDto.getDate());
        movieSession.setMovie(movieService.getMovieById(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.getCinemaHallById(movieSessionRequestDto
                .getCinemaHallId()));
        return movieSession;
    }

    public MovieSessionResponseDto getMovieSessionResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieSessionId(movieSession.getId());
        movieSessionResponseDto.setDate(movieSession.getShowTime());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        return movieSessionResponseDto;
    }
}
