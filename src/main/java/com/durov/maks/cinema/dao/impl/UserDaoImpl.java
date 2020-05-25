package com.durov.maks.cinema.dao.impl;

import com.durov.maks.cinema.dao.UserDao;
import com.durov.maks.cinema.exceptions.DataProcessingException;
import com.durov.maks.cinema.lib.Dao;
import com.durov.maks.cinema.model.User;
import com.durov.maks.cinema.util.HibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long userId = (Long) session.save(user);
            user.setId(userId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("can't save user entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            criteriaQuery.select(userRoot).where(criteriaBuilder
                    .equal(userRoot.get("email"), email));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (HibernateException e) {
            throw new DataProcessingException("can't get all movies entity", e);
        }
    }
}
