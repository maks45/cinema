package com.durov.maks.cinema.dao;

import com.durov.maks.cinema.model.ShoppingCart;
import com.durov.maks.cinema.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
