package com.vraenchike.Tests;

import com.vraenchike.Exception.PlaceNotExist;
import com.vraenchike.Exception.UserCredentialAlreadyExist;
import com.vraenchike.Exception.UserNotAuth;
import com.vraenchike.Model.Place;
import com.vraenchike.Model.User;
import com.vraenchike.Services.DAO.PlaceDAOImpl;
import com.vraenchike.Services.EntityServises.PlaceService;
import com.vraenchike.Services.EntityServises.UserService;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Created by Alexeev on 03-May-15.
 */
public class testFavoritePlace extends Assert {
    UserService service  = new UserService();
   PlaceService placeService = new PlaceService();
    String u = "";
    String login = "";
    String pass = "";


    public String getRandomString()
    {
        String s = "";
        Random r = new Random();
        String letters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

        for(int i=0;i<15;i++)
        {
            s+=letters.charAt(r.nextInt(letters.length()));
        }
        return s;
    }

    public testFavoritePlace() {

        this.u = getRandomString();
        this.login = getRandomString();
        this.pass = getRandomString();
    }

    @Test
    public void testAddRemove()
    {
        ArrayList<Place> places = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        try {
            user = service.registerUser(u,login,pass,session);
        } catch (UserCredentialAlreadyExist userCredentialAlreadyExist) {
            fail("USER ALREADY EXIST?");
        }
        session.close();

        Random r = new Random();
        int count = r.nextInt(30)+10;
        for(int i =0 ;i<count;i++)
        {
            Place p = null;
            try {
                p = placeService.AddPlace(r.nextDouble(), r.nextDouble(), r.nextInt(), getRandomString());
            } catch (SQLException e) {
                fail(e.getMessage());
            } catch (UserNotAuth userNotAuth) {
                userNotAuth.printStackTrace();
            }
            assertNotNull(p);
            places.add(p);

        }
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            assertEquals(places.size(),UserService.getCurrentUser(session).getPlaces().size());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
        Set<Place> places1  =null;
        try {
           places1  = UserService.getCurrentUser(session).getPlaces();

        } catch (SQLException e) {
            fail(e.getMessage());
        }

        assertNotNull(places1);
        Iterator<Place> iterator = places1.iterator();
        while (iterator.hasNext()){
            Place next = iterator.next();
            for(Place cur_place:places){
                if(cur_place.getId()==next.getId()){
                    assertEquals(cur_place.getLat(),next.getLat(),0.1);

                    assertEquals(cur_place.getLng(),next.getLng(),0.1);
                    assertEquals(cur_place.getRadius(),next.getRadius());
                    assertEquals(cur_place.getName(),next.getName());
                }
            }

        }
        session.close();
        int removeCount = places.size()/2;
        for(int i=0;i<removeCount;i++)
        {
            try {
                placeService.RemovePlace(places.get(i).getId());
            } catch (SQLException e) {
                fail(e.getMessage());
            } catch (PlaceNotExist placeNotExist) {
                placeNotExist.printStackTrace();
            } catch (UserNotAuth userNotAuth) {
                userNotAuth.printStackTrace();
            }

        }
        session = HibernateUtil.getSessionFactory().openSession();


        try {
            user = UserService.getCurrentUser(session);
        } catch (SQLException e) {
           fail(e.getMessage());
        }
        assertEquals(user.getPlaces().size(),places.size()-removeCount);




        session.close();
    }
}
