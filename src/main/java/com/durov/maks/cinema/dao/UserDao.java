package com.durov.maks.cinema.dao;

import com.durov.maks.cinema.model.User;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
