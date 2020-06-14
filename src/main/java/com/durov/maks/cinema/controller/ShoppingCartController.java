package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.model.dto.shoppingcart.ShoppingCartResponseDto;
import com.durov.maks.cinema.model.mapper.ShoppingCartMapper;
import com.durov.maks.cinema.service.MovieSessionService;
import com.durov.maks.cinema.service.ShoppingCartService;
import com.durov.maks.cinema.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final MovieSessionService movieSessionService;
    private final ShoppingCartMapper shoppingCartMapper;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public ShoppingCartController(MovieSessionService movieSessionService,
                                  ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.movieSessionService = movieSessionService;
        this.shoppingCartMapper = shoppingCartMapper;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication auth) {
        User user = userService.findByEmail(((UserDetails) auth.getPrincipal())
                .getUsername());
        return shoppingCartMapper.getShoppingCartResponseDto(shoppingCartService
                .getByUser(user));
    }

    @PostMapping
    public void addMovieSession(Authentication auth, @RequestParam Long movieSessionId) {
        shoppingCartService.addSession(movieSessionService.getSessionById(movieSessionId),
                userService.findByEmail(((UserDetails) auth.getPrincipal())
                        .getUsername()));
    }
}
