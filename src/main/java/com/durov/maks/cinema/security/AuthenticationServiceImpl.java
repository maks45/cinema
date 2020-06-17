package com.durov.maks.cinema.security;

import com.durov.maks.cinema.exception.AuthenticationException;
import com.durov.maks.cinema.model.Role;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.service.UserService;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserService userService,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (passwordEncoder.matches(password,user.getPassword())) {
            return user;
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public User register(String email, String login, String password, Set<Role> roleSet) {
        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roleSet);
        return userService.add(user);
    }
}
