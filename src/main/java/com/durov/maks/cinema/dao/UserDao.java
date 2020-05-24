package com.durov.maks.cinema.dao;

import com.durov.maks.cinema.model.User;

public interface UserDao {
    User create(User user);

    User findByEmail(String email);
}
