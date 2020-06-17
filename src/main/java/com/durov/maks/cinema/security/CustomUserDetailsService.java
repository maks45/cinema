package com.durov.maks.cinema.security;

import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.service.UserService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private org.springframework.security.core.userdetails.User springUser;
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        UserBuilder userBuilder = springUser.withUsername(user.getEmail());
        userBuilder.roles(user.getRoles().stream().map(r -> r.getRoleName().name())
                .toArray(String[]::new));
        userBuilder.password(user.getPassword());
        return userBuilder.build();
    }
}
