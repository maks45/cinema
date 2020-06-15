package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.model.dto.user.UserRequestDto;
import com.durov.maks.cinema.security.AuthenticationService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public void register(@RequestBody @Valid UserRequestDto userRequestDto) {
        authenticationService.register(
                userRequestDto.getEmail(),
                userRequestDto.getLogin(),
                userRequestDto.getPassword());
    }
}
