package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.model.dto.order.OrderResponseDto;
import com.durov.maks.cinema.model.mapper.OrderMapper;
import com.durov.maks.cinema.service.OrderService;
import com.durov.maks.cinema.service.ShoppingCartService;
import com.durov.maks.cinema.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderMapper orderMapper, OrderService orderService,
                           UserService userService, ShoppingCartService shoppingCartService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public void completeOrder(Authentication auth) {
        User user = userService.findByEmail(((UserDetails) auth.getPrincipal())
                .getUsername());
        orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(), user);
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders(Authentication auth) {
        return orderService.getOrderHistory(userService
                .findByEmail(((UserDetails) auth.getPrincipal())
                        .getUsername())).stream()
                .map(orderMapper::getOrderDto)
                .collect(Collectors.toList());
    }
}
