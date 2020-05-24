package com.durov.maks.cinema.dao;

import com.durov.maks.cinema.model.User;

public interface UserDao {
    User add(User user);

    User findByEmail(String email);
}
