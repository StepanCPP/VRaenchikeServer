package com.vraenchike.Tests;

import com.vraenchike.Exception.UserCredentialAlreadyExist;
import com.vraenchike.Model.*;
import com.vraenchike.Services.EntityServises.UserService;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;


import java.sql.SQLException;
import java.util.*;

/**
 * Created by Alexeev on 02-May-15.
 */
public class UserServiceTest extends Assert {
    UserService service = new UserService();
    String u = "";
    String login = "";
    String pass = "";
    TreeSet<Photo> photos = null;//added photo

    /* String []tablesPhotoPlaceBanned = {"UserPhoto","UserPlaces","UserBanned",
            "Photo","Place","Banned"};

    public void truncatePhotosPlacesBanes(String[] tables)
    {


        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        for(String table : tables){
            s.createQuery("delete from "+table).executeUpdate();
        }



        s.getTransaction().commit();
        service = new UserService();
    }*/
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

    public UserServiceTest()
    {
        this.u = getRandomString();
        this.login = getRandomString();
        this.pass = getRandomString();
    }



    @Test
    public void testAddRemoveFavoritePhoto()
    {

        Session s = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        try {
            user = service.registerUser(u,login,pass,s);
        } catch (UserCredentialAlreadyExist userCredentialAlreadyExist) {
            fail("USER ALREADY EXIST?");
        }

        s.close();


        photos = new TreeSet<>();
        int count = new Random().nextInt(30)+10;
        for(int i=0;i<count;i++){
            String url = getRandomString();
            int dis = new Random().nextInt(12345);
            int likes = new Random().nextInt(12345);
            //Photo p = new Photo(url,likes,dis);
            try {
                service.AddFavoritePhoto(url);
                photos.add(new Photo(url,likes,dis));


            } catch (SQLException e) {
                fail(e.getMessage());
            }
        }


       s = HibernateUtil.getSessionFactory().openSession();

        assertNotNull(UserService.lastRegisterUserLogin);
        org.hibernate.Query query = s.createQuery("from User where userLoginInfo.login = :login_inf");
        query.setParameter("login_inf",login);
        User suser = (User) query.uniqueResult();
        assertNotNull(suser);

        assertEquals( suser.getUserPhoto().size(),count);
        Iterator<UserPhoto> it = suser.getUserPhoto().iterator();
        while (it.hasNext()){
            assertTrue(photos.contains(it.next().getPhoto()));
        }
        s.close();




        int toRemoveCount = new Random().nextInt(this.photos.size()-5)+5;
        TreeSet<Photo> removedPhoto = new TreeSet<>();

        int counter = 0;
        Iterator<Photo> userPhotoIterator = this.photos.iterator();
        while (userPhotoIterator.hasNext() && counter++<toRemoveCount){
            try {
                Photo next = userPhotoIterator.next();
                service.RemoveFavoritePhoto(next.getUrl());
                removedPhoto.add(next);
            } catch (SQLException e) {
                fail(e.getMessage());
            }
        }


      s = HibernateUtil.getSessionFactory().openSession();
       query = s.createQuery("from User where userLoginInfo.login = :login_inf");
        query.setParameter("login_inf",login);
        suser = (User) query.uniqueResult();
        assertNotNull(suser);





        TreeSet<Photo> userFavoritePhoto = suser.getFavoritePhoto();
        Iterator<Photo> it1 = userFavoritePhoto.iterator();
        while (it1.hasNext()){
            assertFalse(removedPhoto.contains(it1.next()));
        }


        s.close();
    }



}
