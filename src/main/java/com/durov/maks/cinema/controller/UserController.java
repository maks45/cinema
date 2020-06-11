package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.model.dto.user.UserResponseDto;
import com.durov.maks.cinema.model.mapper.UserMapper;
import com.durov.maks.cinema.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public UserResponseDto getUserByEmail(@RequestParam String email) {
        return userMapper.getUserResponseDto(userService.findByEmail(email));
    }
}
