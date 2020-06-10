package com.durov.maks.cinema.service.impl;

import com.durov.maks.cinema.dao.UserDao;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.service.ShoppingCartService;
import com.durov.maks.cinema.service.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final ShoppingCartService shoppingCartService;

    public UserServiceImpl(UserDao userDao, ShoppingCartService shoppingCartService) {
        this.userDao = userDao;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User add(User user) {
        user = userDao.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User getUserById(Long userId) {
        return userDao.getById(userId);
    }
}
