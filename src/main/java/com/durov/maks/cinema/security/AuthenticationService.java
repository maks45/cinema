package com.durov.maks.cinema.security;

import com.durov.maks.cinema.exception.AuthenticationException;
import com.durov.maks.cinema.model.Role;
import com.durov.maks.cinema.model.User;
import java.util.Set;

public interface AuthenticationService {

    User register(String email, String login, String password, Set<Role> roles);
}
