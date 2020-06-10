package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.model.CinemaHall;
import com.durov.maks.cinema.model.dto.cinemahall.CinemaHallRequestDto;
import com.durov.maks.cinema.model.dto.cinemahall.CinemaHallResponseDto;
import com.durov.maks.cinema.service.CinemaHallService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinemahalls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping
    public void addCinemaHall(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(getCinemaHall(cinemaHallRequestDto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return cinemaHallService.getAll().stream()
                .map(this::getCinemaHallDto)
                .collect(Collectors.toList());
    }

    private CinemaHall getCinemaHall(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        return cinemaHall;
    }

    private CinemaHallResponseDto getCinemaHallDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setId(cinemaHall.getId());
        cinemaHall.setCapacity(cinemaHall.getCapacity());
        cinemaHall.setDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }
}
