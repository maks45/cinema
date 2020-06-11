package com.durov.maks.cinema.model.mapper;

import com.durov.maks.cinema.model.Order;
import com.durov.maks.cinema.model.dto.order.OrderResponseDto;
import com.durov.maks.cinema.service.ShoppingCartService;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ShoppingCartService shoppingCartService;

    public OrderMapper(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public OrderResponseDto getOrderDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setShoppingCartId(shoppingCartService.getByUser(order.getUser()).getId());
        return orderResponseDto;
    }
}
