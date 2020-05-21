package com.durov.maks.cinema.dao.impl;

import com.durov.maks.cinema.dao.MovieSessionDao;
import com.durov.maks.cinema.exceptions.DataProcessingException;
import com.durov.maks.cinema.lib.Dao;
import com.durov.maks.cinema.model.MovieSession;
import com.durov.maks.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> query = criteriaBuilder.createQuery(MovieSession.class);
            Root<MovieSession> root = query.from(MovieSession.class);
            query.select(root).where(criteriaBuilder.equal(root.get("movie"), movieId));
            query.select(root).where(criteriaBuilder.greaterThanOrEqualTo(root.get("showTime"),
                    date.atStartOfDay()));
            return session.createQuery(query).getResultList();
        } catch (HibernateException e) {
            throw new DataProcessingException("can't find available Sessions ", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long movieSessionId = (Long) session.save(movieSession);
            movieSession.setId(movieSessionId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("can't save movie session entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return movieSession;
    }
}
