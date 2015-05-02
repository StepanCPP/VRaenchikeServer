package com.vraenchike.Services.EntityServises;

import com.vraenchike.Model.Banned;
import com.vraenchike.Model.Place;
import com.vraenchike.Model.User;
import com.vraenchike.Services.DAO.DAOFactory;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Artyom on 29.04.2015.
 */
public class PlaceService {

    public void AddPlace(int lng, int lat, int radius, String name) throws SQLException {
        Place p = new Place(lng,lat,radius,name);

        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = UserService.getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            return;
        }

        user.getPlaces().add(p);
        session.save(p);
    }
    public void RemovePlace(long id) throws SQLException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = UserService.getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            return;
        }
        Iterator<Place> iterator = user.getPlaces().iterator();
        while (iterator.hasNext()) {
            Place element = iterator.next();
            if (element.getId()== id) {
                iterator.remove();
            }
        }
        session.save(user);
    }
   public Collection<Place> GetAllPlaces() throws SQLException {
       Session session = HibernateUtil.getSessionFactory().openSession();
       User user = UserService.getCurrentUser(session);
       if(user==null){
           if (session != null && session.isOpen())
               session.close();
           return new ArrayList<>();
       }
       return user.getPlaces();
   }
}
