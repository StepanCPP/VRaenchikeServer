package com.vraenchike.Services.EntityServises;

import com.vraenchike.Exception.PlaceNotExist;
import com.vraenchike.Exception.UserNotAuth;
import com.vraenchike.Model.Banned;
import com.vraenchike.Model.Place;
import com.vraenchike.Model.User;
import com.vraenchike.Services.DAO.DAOFactory;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONException;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Artyom on 29.04.2015.
 */
public class PlaceService {

    public Place AddPlace(double lng, double lat, int radius, String name) throws SQLException, UserNotAuth {
        Place p = new Place(lng,lat,radius,name);

        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = UserService.getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            throw new UserNotAuth();
        }
        session.beginTransaction();
        user.getPlaces().add(p);
        session.save(p);
        session.save(user);
        session.flush();
        session.clear();
        session.getTransaction().commit();
        return p;
    }
    public Place RemovePlace(long id) throws SQLException, UserNotAuth, PlaceNotExist {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = UserService.getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            throw new UserNotAuth();
        }
        Iterator<Place> iterator = user.getPlaces().iterator();
//asd
        Place toReturn = null;
        while (iterator.hasNext()) {
            Place element = iterator.next();
            if (element.getId()== id) {
                iterator.remove();
                toReturn = element;
            }
        }
        session.save(user);
        session.flush();
        session.clear();
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
        if(toReturn==null){
            throw  new PlaceNotExist();
        }
        return toReturn;
    }
   public JSONArray GetAllPlaces() throws SQLException, UserNotAuth, JSONException {
       Session session = HibernateUtil.getSessionFactory().openSession();
       User user = UserService.getCurrentUser(session);
       if(user==null){
           if (session != null && session.isOpen())
               session.close();
           throw new UserNotAuth();
       }
       Set<Place> places = user.getPlaces();

       JSONArray array = new JSONArray();
       Iterator<Place> iterator = places.iterator();
       while (iterator.hasNext())
           array.put(iterator.next().toJSONObject());


       session.close();

       return array;

   }
    public Place updatePlace(int id,double lat,double lng,int radius,String name) throws SQLException, PlaceNotExist, UserNotAuth {
        Session session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
        User user = UserService.getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            throw new UserNotAuth();
        }
        Query query = session.createQuery("from Place where id=:id");
        query.setParameter("id",id);
        Object o = query.uniqueResult();
        if(o==null){
            throw  new PlaceNotExist();
        }
        Place place = (Place) o;
        place.setLat(lat);
        place.setLng(lng);
        place.setRadius(radius);
        place.setName(name);
        session.save(place);
        session.flush();
        session.clear();
        session.getTransaction().commit();
        session.close();
        return place;
    }

}
