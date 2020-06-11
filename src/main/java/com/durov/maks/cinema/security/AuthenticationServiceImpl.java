package com.durov.maks.cinema.security;

import com.durov.maks.cinema.exception.AuthenticationException;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.service.UserService;
import com.durov.maks.cinema.util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final HashUtil hashUtil;

    public AuthenticationServiceImpl(UserService userService, HashUtil hashUtil) {
        this.userService = userService;
        this.hashUtil = hashUtil;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user.getPassword().equals(hashUtil.hashPassword(password, user.getSalt()))) {
            return user;
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public User register(String email, String login, String password) {
        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setSalt(hashUtil.getSalt());
        user.setPassword(hashUtil.hashPassword(password, user.getSalt()));
        return userService.add(user);
    }
}
