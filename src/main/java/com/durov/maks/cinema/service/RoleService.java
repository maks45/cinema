package com.durov.maks.cinema.service;

import com.durov.maks.cinema.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
