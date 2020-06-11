package com.durov.maks.cinema.model.mapper;

import com.durov.maks.cinema.model.ShoppingCart;
import com.durov.maks.cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public ShoppingCartResponseDto getShoppingCartResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setTicketResponseDtos(
                shoppingCart.getTickets().stream().map(ticketMapper::getTicketResponseDto)
                        .collect(Collectors.toList()));
        shoppingCartResponseDto.setUserLogin(shoppingCart.getUser().getLogin());
        shoppingCartResponseDto.setUserId(shoppingCart.getUser().getId());
        return shoppingCartResponseDto;
    }
}
