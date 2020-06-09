package com.durov.maks.cinema.dao.impl;

import com.durov.maks.cinema.dao.OrderDao;
import com.durov.maks.cinema.exception.DataProcessingException;
import com.durov.maks.cinema.model.Order;
import com.durov.maks.cinema.model.User;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order createOrder(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("can't save order entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return order;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = criteriaBuilder
                    .createQuery(Order.class);
            Root<Order> root = criteriaQuery.from(Order.class);
            root.fetch("tickets", JoinType.LEFT);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user"), user));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            throw new DataProcessingException("can't get shopping cart by user with id "
                    + user.getId(), e);
        }
    }
}
