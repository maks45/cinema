package com.durov.maks.cinema.dao;

import com.durov.maks.cinema.model.Order;
import com.durov.maks.cinema.model.Ticket;
import com.durov.maks.cinema.model.User;
import java.util.List;

public interface OrderDao {
    Order createOrder(Order order);

    List<Order> getUserOrders(User user);
}
