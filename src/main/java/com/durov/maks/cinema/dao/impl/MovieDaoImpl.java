package com.durov.maks.cinema.dao.impl;

import com.durov.maks.cinema.dao.MovieDao;
import com.durov.maks.cinema.exception.DataProcessingException;
import com.durov.maks.cinema.model.Movie;
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
public class MovieDaoImpl implements MovieDao {
    private final SessionFactory sessionFactory;

    public MovieDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
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
        return movie;
    }

    public List<Movie> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            throw new DataProcessingException("can't get all movies entity", e);
        }
    }

    @Override
    public Movie getById(Long movieId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Movie> criteriaQuery = criteriaBuilder.createQuery(Movie.class);
            Root<Movie> movieRoot = criteriaQuery.from(Movie.class);
            criteriaQuery.select(movieRoot).where(criteriaBuilder
                    .equal(movieRoot.get("id"), movieId));
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (HibernateException e) {
            throw new DataProcessingException("can't get movie with id " + movieId, e);
        }
    }
}
