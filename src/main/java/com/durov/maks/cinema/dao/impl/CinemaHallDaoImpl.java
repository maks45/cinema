package com.durov.maks.cinema.dao.impl;

import com.durov.maks.cinema.dao.CinemaHallDao;
import com.durov.maks.cinema.lib.Dao;
import com.durov.maks.cinema.model.CinemaHall;
import com.durov.maks.cinema.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long movieId = (Long) session.save(cinemaHall);
            cinemaHall.setId(movieId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("can't save cinema hall entity", e);
        }
        return cinemaHall;
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("can't get all cinema hall entity", e);
        }
    }
}
