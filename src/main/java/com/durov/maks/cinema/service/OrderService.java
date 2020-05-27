package com.durov.maks.cinema.service;

import com.durov.maks.cinema.model.Order;
import com.durov.maks.cinema.model.Ticket;
import com.durov.maks.cinema.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
