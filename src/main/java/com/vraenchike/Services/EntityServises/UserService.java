package com.vraenchike.Services.EntityServises;

import com.vraenchike.Model.*;
import com.vraenchike.Services.DAO.DAOFactory;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.SQLException;
import java.util.Iterator;

/**
 * Created by Artyom on 29.04.2015.
 */
public class UserService {


    public static User getCurrentUser(Session session) throws SQLException {
       session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        User u = (User)criteria.add(Restrictions.eq("login", SecurityContextHolder.getContext().getAuthentication().getName()))
                .uniqueResult();
        session.getTransaction().commit();
        return u;

    }

    public void AddFavoritePhoto( String url) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            return;
        }

        session.beginTransaction();

        Criteria criteria = session.createCriteria(Photo.class);
        Photo p = (Photo)criteria.add(Restrictions.eq("url", url))
                .uniqueResult();

        if (p == null){
            p = new Photo(url,0,0);
        }

        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setPhoto(p);
        userPhoto.setType("like");
        userPhoto.setUser(user);
        user.getUserPhoto().add(userPhoto);
       // p.getUsers().add(user);

        session.save(user);
        session.save(p);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();



    }
    public void RemoveFavoritePhoto( String url) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            return;
        }


        session.beginTransaction();
        Photo p = DAOFactory.getInstance().getPhotoDAO().getByURL(url,session);

        Iterator<UserPhoto> it = user.getUserPhoto().iterator();
        while(it.hasNext()){
            UserPhoto us = it.next();
            if(us.getPhoto().equals(p)){
                it.remove();
            }
        }



        session.save(user);
        session.getTransaction().commit();
        //DAOFactory.getInstance().getUserDAO().updateUser(uid,user,session);

        if (session != null && session.isOpen())
            session.close();

    }
    public void AddFavoritePlace( long place_id) throws SQLException {
        Session session =  HibernateUtil.getSessionFactory().openSession();
        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            return;
        }

        long pid = place_id;

        Place p = DAOFactory.getInstance().getPlaceDAO().getPlaceById(pid,session);
        if (p == null){
            DAOFactory.getInstance().getPlaceDAO().addPlace(p,session);
        }

        user.getPlaces().add(p);
        session.save(user);

        if (session != null && session.isOpen())
            session.close();

    }
    public void RemoveFavoritePlace(long place_id) throws SQLException {
        long pid = place_id;

        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            return;
        }

        Place p = DAOFactory.getInstance().getPlaceDAO().getPlaceById(pid,session);
        user.getPlaces().remove(p);
        session.save(user);
        if (session != null && session.isOpen())
            session.close();


    }
    public void Ban(String linkVK,String linkInsta) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            return;
        }
        Banned banned = new Banned();
        banned.setLinkInsta(linkInsta);
        banned.setLinkVk(linkVK);
        user.getBanned().add(banned);
        session.save(banned);
        session.save(user);

    }
    public void DisBan(int idBanned) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            return;
        }
        Iterator<Banned> iterator = user.getBanned().iterator();
        while (iterator.hasNext()) {
            Banned element = iterator.next();
            if (element.getIdBanned() == idBanned) {
                iterator.remove();
            }
        }
        session.save(user);
    }
}
