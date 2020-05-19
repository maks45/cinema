package com.durov.maks.cinema.dao;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import com.durov.maks.cinema.lib.Dao;
import com.durov.maks.cinema.model.Movie;
import com.durov.maks.cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieDaoImpl implements MovieDao {
    private static final Logger LOGGER = Logger.getLogger(MovieDaoImpl.class);

    public Movie add(Movie movie) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(movie);
            transaction.commit();
            movie.setId(itemId);
        }catch (HibernateException e){
            if(transaction != null){
                transaction.rollback();
            }
            throw  new RuntimeException("can't save movie entity", e);
        }
        return movie;
    }

    public List<Movie> getAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        }catch (HibernateException e){
            throw  new RuntimeException("can't save movie entity", e);
        }
    }
}
