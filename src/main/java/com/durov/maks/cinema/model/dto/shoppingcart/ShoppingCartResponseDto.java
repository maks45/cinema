package com.durov.maks.cinema.model.dto.shoppingcart;

import com.durov.maks.cinema.model.dto.ticket.TicketResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ShoppingCartResponseDto {
    private Long id;
    private String userLogin;
    private Long userId;
    @JsonProperty("tickets")
    private List<TicketResponseDto> ticketResponseDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TicketResponseDto> getTicketResponseDtos() {
        return ticketResponseDtos;
    }

    public void setTicketResponseDtos(List<TicketResponseDto> ticketResponseDtos) {
        this.ticketResponseDtos = ticketResponseDtos;
    }
}
