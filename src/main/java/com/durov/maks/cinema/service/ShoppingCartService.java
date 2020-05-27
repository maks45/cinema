package com.durov.maks.cinema.service;

import com.durov.maks.cinema.model.MovieSession;
import com.durov.maks.cinema.model.ShoppingCart;
import com.durov.maks.cinema.model.User;

public interface ShoppingCartService {
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void deleteAllTickets(ShoppingCart shoppingCart);
}
