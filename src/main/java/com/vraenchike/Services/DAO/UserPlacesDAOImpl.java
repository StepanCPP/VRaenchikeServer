package com.vraenchike.Services.DAO;

import com.vraenchike.Model.UserPlaces;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Artyom on 15.04.2015.
 */
public class UserPlacesDAOImpl implements UserPlacesDAO {
    @Override
    public void addUserPlaces(UserPlaces user_places) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user_places);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void updateUserPlaces(Long user_places_id, UserPlaces user_places) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user_places);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public UserPlaces getUserPlacesById(Long user_places_id) throws SQLException {
        Session session = null;
        UserPlaces user_places = null;
        session = HibernateUtil.getSessionFactory().openSession();
        user_places = (UserPlaces)session.load(UserPlaces.class, user_places_id);
        if (session != null && session.isOpen())
            session.close();
        return user_places;
    }

    @Override
    public Collection getAllUserPlacess() throws SQLException {
        Session session = null;
        List user_placess = new ArrayList<UserPlaces>();
        session = HibernateUtil.getSessionFactory().openSession();
        user_placess = session.createCriteria(UserPlaces.class).list();
        if (session != null && session.isOpen())
            session.close();
        return user_placess;
    }

    @Override
    public void deleteUserPlaces(UserPlaces user_places) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(user_places);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();

    }
}
