package com.durov.maks.cinema.service;

import com.durov.maks.cinema.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user);

    User findByEmail(String email);

    User getUserById(Long userId);
}
