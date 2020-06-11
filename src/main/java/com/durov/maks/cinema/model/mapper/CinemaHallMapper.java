package com.durov.maks.cinema.model.mapper;

import com.durov.maks.cinema.model.CinemaHall;
import com.durov.maks.cinema.model.dto.cinemahall.CinemaHallRequestDto;
import com.durov.maks.cinema.model.dto.cinemahall.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallMapper {

    public CinemaHall getCinemaHall(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        return cinemaHall;
    }

    public CinemaHallResponseDto getCinemaHallResponseDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setId(cinemaHall.getId());
        cinemaHall.setCapacity(cinemaHall.getCapacity());
        cinemaHall.setDescription(cinemaHall.getDescription());
        return cinemaHallResponseDto;
    }
}
