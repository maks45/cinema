package com.durov.maks.cinema.controller;

import com.durov.maks.cinema.model.Role;
import com.durov.maks.cinema.security.AuthenticationService;
import com.durov.maks.cinema.service.RoleService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InjectController {
    private final RoleService roleService;
    private final AuthenticationService authenticationService;

    public InjectController(RoleService roleService, AuthenticationService authenticationService) {
        this.roleService = roleService;
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    public void injectToDatabase() {
        Role userRole = Role.of("USER");
        roleService.add(userRole);
        Role adminRole = Role.of("ADMIN");
        roleService.add(adminRole);
        authenticationService.register("admin@gmail.com", "admin",
                "1111", Set.of(adminRole, userRole));
    }
}
