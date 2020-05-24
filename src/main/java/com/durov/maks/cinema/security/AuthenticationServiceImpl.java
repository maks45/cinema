package com.durov.maks.cinema.security;

import com.durov.maks.cinema.exceptions.AuthenticationException;
import com.durov.maks.cinema.lib.Inject;
import com.durov.maks.cinema.lib.Service;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.service.UserService;
import com.durov.maks.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        return userService.findByEmail(email)
                .filter(u -> u.getPassword().equals(HashUtil.hashPassword(password, u.getSalt())))
                .orElseThrow(() -> new AuthenticationException("Incorrect login or password"));
    }

    @Override
    public User register(String email, String login, String password) {
        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(password,user.getSalt()));
        return userService.add(user);
    }
}
