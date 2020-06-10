package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.exception.AuthenticationException;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.model.dto.user.UserResponseDto;
import com.durov.maks.cinema.service.ShoppingCartService;
import com.durov.maks.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public UserController(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return getUserResponseDto(userService.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException(String
                        .format("user with email: %s not found", email))));
    }

    private UserResponseDto getUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getId());
        userResponseDto.setShoppingCartId(shoppingCartService.getByUser(user).getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setLogin(user.getLogin());
        return userResponseDto;
    }
}
