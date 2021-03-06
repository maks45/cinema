package com.durov.maks.cinema.service.impl;

import com.durov.maks.cinema.dao.ShoppingCartDao;
import com.durov.maks.cinema.dao.TicketDao;
import com.durov.maks.cinema.model.MovieSession;
import com.durov.maks.cinema.model.ShoppingCart;
import com.durov.maks.cinema.model.Ticket;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.service.MovieSessionService;
import com.durov.maks.cinema.service.ShoppingCartService;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;
    private final MovieSessionService movieSessionService;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao,
                                   TicketDao ticketDao,
                                   MovieSessionService movieSessionService) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
        this.movieSessionService = movieSessionService;
    }

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setMovieSession(movieSession);
        ticket.setUser(user);
        ticketDao.add(ticket);
        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user);
        shoppingCart.getTickets().add(ticket);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}
