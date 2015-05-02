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

/**
 * Created by Artyom on 29.04.2015.
 */
public class UserService {


    public User getCurrentUser(Session session) throws SQLException {
       session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        User u = (User)criteria.add(Restrictions.eq("login", SecurityContextHolder.getContext().getAuthentication().getName()))
                .uniqueResult();
        session.getTransaction().commit();
        return u;

    }
    public User getUserById(long id,Session session) throws SQLException {
        User user = DAOFactory.getInstance().getUserDAO().getUserById(id,session);
        return user;
    }
    public void AddFavoritePhoto(long user_id, String url) throws SQLException {
        long uid = user_id;
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        User user = user = (User)session.load(User.class, user_id);

        Criteria criteria = session.createCriteria(Photo.class);
        Photo p = (Photo)criteria.add(Restrictions.eq("url", url))
                .uniqueResult();

        if (p == null){
            p = new Photo(url,0,0);
        }

        user.getPhotos().add(p);
       // p.getUsers().add(user);

        session.save(user);
        session.save(p);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();



    }
    public void RemoveFavoritePhoto(long id, String url) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long uid = id;
        session.beginTransaction();
        User user = DAOFactory.getInstance().getUserDAO().getUserById(uid,session);
        Photo p = DAOFactory.getInstance().getPhotoDAO().getByURL(url,session);
        user.getPhotos().remove(p);


        session.save(user);
        session.getTransaction().commit();
        //DAOFactory.getInstance().getUserDAO().updateUser(uid,user,session);



    }
    public void AddFavoritePlace(long user_id, long place_id) throws SQLException {
        long pid = user_id;
        long uid = place_id;
        Session session =  HibernateUtil.getSessionFactory().openSession();
        Place p = DAOFactory.getInstance().getPlaceDAO().getPlaceById(pid);
        if (p == null){
            DAOFactory.getInstance().getPlaceDAO().addPlace(p);
        }
        User u = DAOFactory.getInstance().getUserDAO().getUserById(uid,session);
        u.getPlaces().add(p);
        DAOFactory.getInstance().getUserDAO().updateUser(uid,u,session);
    }
    public void RemoveFavoritePlace(long user_id, long place_id) throws SQLException {
        long pid = user_id;
        long uid = place_id;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Place p = DAOFactory.getInstance().getPlaceDAO().getPlaceById(pid);
        User u = DAOFactory.getInstance().getUserDAO().getUserById(uid,session);
        u.getPlaces().remove(p);
        DAOFactory.getInstance().getUserDAO().updateUser(uid,u,session);
    }
    public void Ban(User user, Banned banned) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long uid = user.getIdUser();
        long buid = banned.getIdBanned();
        User u = DAOFactory.getInstance().getUserDAO().getUserById(uid,session);
        u.getBanned().add(banned);
        DAOFactory.getInstance().getUserDAO().updateUser(uid,u,session);
    }
    public void DisBan(User user, Banned banned) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        long uid = user.getIdUser();
        long buid = banned.getIdBanned();
        User u = DAOFactory.getInstance().getUserDAO().getUserById(uid,session);
        u.getBanned().remove(banned);
        DAOFactory.getInstance().getUserDAO().updateUser(uid,u,session);
    }
}
