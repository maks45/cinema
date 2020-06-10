package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.model.Order;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.model.dto.order.OrderResponseDto;
import com.durov.maks.cinema.service.OrderService;
import com.durov.maks.cinema.service.ShoppingCartService;
import com.durov.maks.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService,
                           UserService userService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public void completeOrder(@RequestBody Long userId) {
        User user = userService.getUserById(userId);
        orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.getUserById(userId)).stream()
                .map(this::getOrderDto)
                .collect(Collectors.toList());
    }

    private OrderResponseDto getOrderDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setShoppingCartId(shoppingCartService.getByUser(order.getUser()).getId());
        return orderResponseDto;
    }
}
