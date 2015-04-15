package com.vraenchike.Services.DAO;

import com.vraenchike.Model.User;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Artyom on 15.04.2015.
 */
public class UserDAOImpl implements UserDAO {
    @Override
    public void addUser(User user) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void updateUser(Long user_id, User user) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public User getUserById(Long user_id) throws SQLException {
        Session session = null;
        User user = null;
        session = HibernateUtil.getSessionFactory().openSession();
        user = (User)session.load(User.class, user_id);
        if (session != null && session.isOpen())
            session.close();
        return user;
    }

    @Override
    public Collection getAllUsers() throws SQLException {
        Session session = null;
        List users = new ArrayList<User>();
        session = HibernateUtil.getSessionFactory().openSession();
        users = session.createCriteria(User.class).list();
        if (session != null && session.isOpen())
            session.close();
        return users;
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();

    }
}
