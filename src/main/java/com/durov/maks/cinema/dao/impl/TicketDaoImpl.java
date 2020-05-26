package com.durov.maks.cinema.dao.impl;

import com.durov.maks.cinema.dao.TicketDao;
import com.durov.maks.cinema.exceptions.DataProcessingException;
import com.durov.maks.cinema.lib.Dao;
import com.durov.maks.cinema.model.Ticket;
import com.durov.maks.cinema.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("can't save ticket entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return ticket;
    }
}
