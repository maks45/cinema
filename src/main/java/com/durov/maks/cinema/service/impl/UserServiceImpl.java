package com.durov.maks.cinema.service.impl;

import com.durov.maks.cinema.dao.UserDao;
import com.durov.maks.cinema.lib.Inject;
import com.durov.maks.cinema.lib.Service;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.service.UserService;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
