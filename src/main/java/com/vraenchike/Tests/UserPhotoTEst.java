package com.vraenchike.Tests;

import com.vraenchike.Exception.PhotoAlreadyAddedeException;
import com.vraenchike.Exception.PhotoAlreadyDisliked;
import com.vraenchike.Exception.PhotoAlreadyLiked;
import com.vraenchike.Exception.UserNotAuth;
import com.vraenchike.Model.Likes;
import com.vraenchike.Model.Photo;
import com.vraenchike.Services.EntityServises.PhotoService;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alexeev on 03-May-15.
 */
public class UserPhotoTEst extends Assert {
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
    PhotoService photoService = new PhotoService();
    ArrayList<String> liked = new ArrayList<>();
    String disliked = "";
    String liked_url= "";
    String myRandomIp = getRandomString();
    @Test
    public void testLikePhoto()
    {

        int count = new Random().nextInt(30)+10;

        for(int i=0;i<count;i++)
        {
            String l = getRandomString();
            liked.add(l);
            liked_url = l;
            try {
                photoService.LikePhoto(l,"asd","vk",myRandomIp,false);
            } catch (SQLException e) {
                fail(e.getMessage());
            } catch (PhotoAlreadyLiked photoAlreadyLiked) {
               fail("PHOTO ALREADY LIKED!?");
            } catch (UserNotAuth userNotAuth) {
                userNotAuth.printStackTrace();
            } catch (PhotoAlreadyAddedeException e) {
                e.printStackTrace();
            }
        }
        Session session = HibernateUtil.getSessionFactory().openSession();

        String querystr = "from Likes where credentials=:credential" +
                " and isuser=:isuser and islike=true";
        Query query = session.createQuery(querystr);
        query.setParameter("credential",myRandomIp);
        query.setParameter("isuser", false);
        List<Likes> list = query.list();

        assertEquals(list.size(),liked.size());


        session.close();
    }

    @Test
    public void testDislike()
    {
        int count = new Random().nextInt(30)+10;

        for(int i=0;i<count;i++)
        {
            String l = getRandomString();

            disliked = l;
            try {
                photoService.DislikePhoto(l,"asd","vk",myRandomIp,false);
            } catch (SQLException e) {
                fail(e.getMessage());
            } catch (PhotoAlreadyDisliked photoAlreadyDisliked) {
                fail("PHOTO ALREADY DISLIKED!?");
            } catch (UserNotAuth userNotAuth) {
                userNotAuth.printStackTrace();
            } catch (PhotoAlreadyAddedeException e) {
                e.printStackTrace();
            }
        }



        Session session = HibernateUtil.getSessionFactory().openSession();

        String querystr = "from Likes where credentials=:credential" +
                " and isuser=:isuser and islike=false";
        Query query = session.createQuery(querystr);
        query.setParameter("credential",myRandomIp);
        query.setParameter("isuser", false);
        List<Likes> list = query.list();

        assertEquals(list.size(),count);


        session.close();
    }

   /*
    @Test
    public void testUnbindPhoto()
    {
        int count = this.liked.size()/2;
        String myRandomIp = getRandomString();
        for(int i=0;i<count;i++)
        {
            disliked = liked.get(i);
            try {
                photoService.DislikePhoto(liked.get(i),myRandomIp,false);
            } catch (SQLException e) {
                fail(e.getMessage());
            } catch (PhotoAlreadyDisliked photoAlreadyDisliked) {
                fail("PHOTO ALREADY DISLIKED!?");
            }
        }
        Session session = HibernateUtil.getSessionFactory().openSession();

        String querystr = "from Likes where credentials=:credential and isuser=:isuser";
        Query query = session.createQuery(querystr);
        query.setParameter("credential",myRandomIp);
        query.setParameter("isuser", false);
        List<Likes> list = query.list();
        assertEquals(list.size(),liked.size()-count);
        session.close();
    }
*/


    @Test
    public void testAlreadyLikePhoto()
    {

        try {
            photoService.LikePhoto(liked_url,"asd","vk",myRandomIp,false);
            photoService.LikePhoto(liked_url,"asd","vk",myRandomIp,false);
            fail("photo already liked");
        } catch (SQLException e) {
            fail(e.getMessage());
        } catch (PhotoAlreadyLiked photoAlreadyLiked) {

        } catch (UserNotAuth userNotAuth) {
            userNotAuth.printStackTrace();
        } catch (PhotoAlreadyAddedeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAlreadyDislikePhoto()
    {
        try {
            photoService.DislikePhoto(disliked,"asd","vk", myRandomIp, false);
            fail("photo already dsiliked");
        } catch (SQLException e) {
            fail(e.getMessage());
        }  catch (PhotoAlreadyDisliked photoAlreadyDisliked) {
            photoAlreadyDisliked.printStackTrace();
        } catch (UserNotAuth userNotAuth) {
            userNotAuth.printStackTrace();
        } catch (PhotoAlreadyAddedeException e) {
            e.printStackTrace();
        }
    }

}
