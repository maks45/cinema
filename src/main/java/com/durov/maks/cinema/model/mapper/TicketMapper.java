package com.durov.maks.cinema.model.mapper;

import com.durov.maks.cinema.model.Ticket;
import com.durov.maks.cinema.model.dto.ticket.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketResponseDto getTicketResponseDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setId(ticket.getId());
        ticketResponseDto.setCinemaHallId(ticket.getMovieSession().getCinemaHall().getId());
        ticketResponseDto.setMovieId(ticket.getMovieSession().getMovie().getId());
        ticketResponseDto.setMovieTitle(ticket.getMovieSession().getMovie().getTitle());
        ticketResponseDto.setDate(ticket.getMovieSession().getShowTime());
        return ticketResponseDto;
    }
}
