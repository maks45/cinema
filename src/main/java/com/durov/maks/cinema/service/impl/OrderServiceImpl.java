package com.durov.maks.cinema.service.impl;

import com.durov.maks.cinema.dao.OrderDao;
import com.durov.maks.cinema.lib.Inject;
import com.durov.maks.cinema.lib.Service;
import com.durov.maks.cinema.model.Order;
import com.durov.maks.cinema.model.ShoppingCart;
import com.durov.maks.cinema.model.Ticket;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.service.OrderService;
import com.durov.maks.cinema.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setTickets(List.copyOf(tickets));
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        shoppingCartService.deleteAllTickets(shoppingCart);
        return orderDao.createOrder(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getUserOrders(user);
    }
}
