package com.durov.maks.cinema.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.durov.maks.cinema.dao.OrderDao;
import com.durov.maks.cinema.exceptions.DataProcessingException;
import com.durov.maks.cinema.lib.Dao;
import com.durov.maks.cinema.model.Order;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.util.HibernateUtil;
import java.util.List;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order createOrder(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
