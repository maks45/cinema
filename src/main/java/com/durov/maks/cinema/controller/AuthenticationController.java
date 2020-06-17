package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.model.Role;
import com.durov.maks.cinema.model.dto.user.UserRequestDto;
import com.durov.maks.cinema.security.AuthenticationService;
import com.durov.maks.cinema.service.RoleService;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RoleService roleService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    RoleService roleService) {
        this.authenticationService = authenticationService;
        this.roleService = roleService;
    }

    @PostMapping
    public void register(@RequestBody @Valid UserRequestDto userRequestDto) {
        Role role = roleService.getRoleByName("USER");
        authenticationService.register(
                userRequestDto.getEmail(),
                userRequestDto.getLogin(),
                userRequestDto.getPassword(),
                Set.of(role));
    }
}
