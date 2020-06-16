package com.durov.maks.cinema.dao;

import com.durov.maks.cinema.model.Role;

public interface RoleDao {
    void add(Role role);

    Role getRoleByName(String roleName);
}
