package com.vraenchike.Services.DAO;

import com.vraenchike.Model.Place;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Artyom on 15.04.2015.
 */
public class PlaceDAOImpl implements  PlaceDAO {


    @Override
    public void addPlace(Place place) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(place);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
        session.close();
    }

    @Override
    public void updatePlace(Long Place_id, Place place) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(place);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
        session.close();
    }

    @Override
    public Place getPlaceById(Long Place_id) throws SQLException {
        Session session = null;
        Place place = null;
        session = HibernateUtil.getSessionFactory().openSession();
        place = (Place)session.load(Place.class, Place_id);
        if (session != null && session.isOpen())
            session.close();
        return place;
    }

    @Override
    public Collection getAllPlaces() throws SQLException {
        Session session = null;
        List places = new ArrayList <Place>();
        session = HibernateUtil.getSessionFactory().openSession();
        places = session.createCriteria(Place.class).list();
        if (session != null && session.isOpen())
            session.close();
        return places;
    }

    @Override
    public void deletePlace(Place place) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(place);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();

    }
}
