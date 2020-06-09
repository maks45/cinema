package com.durov.maks.cinema.dao.impl;

import com.durov.maks.cinema.dao.ShoppingCartDao;
import com.durov.maks.cinema.exception.DataProcessingException;
import com.durov.maks.cinema.model.ShoppingCart;
import com.durov.maks.cinema.model.User;
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
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private final SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("can't save movie entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> criteriaQuery = criteriaBuilder
                    .createQuery(ShoppingCart.class);
            Root<ShoppingCart> root = criteriaQuery.from(ShoppingCart.class);
            root.fetch("tickets", JoinType.LEFT);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user"), user));
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (HibernateException e) {
            throw new DataProcessingException("can't get shopping cart by user with id "
                    + user.getId(), e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("can't update shopping cart entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
