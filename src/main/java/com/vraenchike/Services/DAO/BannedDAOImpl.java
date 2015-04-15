package com.vraenchike.Services.DAO;

import com.vraenchike.Model.Banned;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Artyom on 15.04.2015.
 */
public class BannedDAOImpl implements BannedDAO {
    @Override
    public void addBanned(Banned banned) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(banned);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void updateBanned(Long banned_id, Banned banned) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(banned);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public Banned getBannedById(Long banned_id) throws SQLException {
        Session session = null;
        Banned banned = null;
        session = HibernateUtil.getSessionFactory().openSession();
        banned = (Banned)session.load(Banned.class, banned_id);
        if (session != null && session.isOpen())
            session.close();
        return banned;
    }

    @Override
    public Collection getAllBanneds() throws SQLException {
        Session session = null;
        List banneds = new ArrayList<Banned>();
        session = HibernateUtil.getSessionFactory().openSession();
        banneds = session.createCriteria(Banned.class).list();
        if (session != null && session.isOpen())
            session.close();
        return banneds;
    }

    @Override
    public void deleteBanned(Banned banned) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(banned);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();

    }
}
