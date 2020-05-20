package com.durov.maks.cinema.dao;

import com.durov.maks.cinema.lib.Dao;
import com.durov.maks.cinema.model.Movie;
import com.durov.maks.cinema.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieDaoImpl implements MovieDao {

    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long movieId = (Long) session.save(movie);
            movie.setId(movieId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("can't save movie entity", e);
        }
        return movie;
    }

    public List<Movie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("can't save movie entity", e);
        }
    }
}
