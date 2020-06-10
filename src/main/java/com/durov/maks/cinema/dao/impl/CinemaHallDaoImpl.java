package com.durov.maks.cinema.dao.impl;

import com.durov.maks.cinema.dao.CinemaHallDao;
import com.durov.maks.cinema.exception.DataProcessingException;
import com.durov.maks.cinema.model.CinemaHall;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    private final SessionFactory sessionFactory;

    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("can't save cinema hall entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return cinemaHall;
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            throw new DataProcessingException("can't get all cinema hall entity", e);
        }
    }

    @Override
    public CinemaHall getById(Long cinemaHallId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CinemaHall> criteriaQuery = criteriaBuilder.createQuery(CinemaHall.class);
            Root<CinemaHall> cinemaHallRoot = criteriaQuery.from(CinemaHall.class);
            criteriaQuery.select(cinemaHallRoot).where(criteriaBuilder
                    .equal(cinemaHallRoot.get("id"), cinemaHallId));
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (HibernateException e) {
            throw new DataProcessingException("can't get cinema hall with id " + cinemaHallId, e);
        }
    }
}
