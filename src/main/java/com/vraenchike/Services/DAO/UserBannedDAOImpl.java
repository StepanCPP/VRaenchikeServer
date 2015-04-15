package com.vraenchike.Services.DAO;

import com.vraenchike.Model.UserBanned;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Artyom on 15.04.2015.
 */
public class UserBannedDAOImpl implements UserBannedDAO {
    @Override
    public void addUserBanned(UserBanned user_banned) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user_banned);
        session.getTransaction();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void updateUserBanned(Long user_banned_id, UserBanned user_banned) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction().commit();
        session.update(user_banned);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public UserBanned getUserBannedById(Long user_banned_id) throws SQLException {
        Session session = null;
        UserBanned user_banned = null;
        session = HibernateUtil.getSessionFactory().openSession();
        user_banned = (UserBanned)session.load(UserBanned.class, user_banned_id);
        if (session != null && session.isOpen())
            session.close();
        return user_banned;
    }

    @Override
    public Collection getAllUserBanneds() throws SQLException {
        Session session = null;
        List user_banneds = new ArrayList<UserBanned>();
        session = HibernateUtil.getSessionFactory().openSession();
        user_banneds = session.createCriteria(UserBanned.class).list();
        if (session != null && session.isOpen())
            session.close();
        return user_banneds;
    }

    @Override
    public void deleteUserBanned(UserBanned user_banned) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(user_banned);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();

    }
}
