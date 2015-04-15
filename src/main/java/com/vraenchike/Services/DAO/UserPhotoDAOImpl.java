package com.vraenchike.Services.DAO;

import com.vraenchike.Model.UserPhoto;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Artyom on 15.04.2015.
 */
public class UserPhotoDAOImpl implements UserPhotoDAO {
    @Override
    public void addUserPhoto(UserPhoto user_photo) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user_photo);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void updateUserPhoto(Long user_photo_id, UserPhoto user_photo) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user_photo);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public UserPhoto getUserPhotoById(Long user_photo_id) throws SQLException {
        Session session = null;
        UserPhoto user_photo = null;
        session = HibernateUtil.getSessionFactory().openSession();
        user_photo = (UserPhoto)session.load(UserPhoto.class, user_photo_id);
        if (session != null && session.isOpen())
            session.close();
        return user_photo;
    }

    @Override
    public Collection getAllUserPhotos() throws SQLException {
        Session session = null;
        List user_photos = new ArrayList<UserPhoto>();
        session = HibernateUtil.getSessionFactory().openSession();
        user_photos = session.createCriteria(UserPhoto.class).list();
        if (session != null && session.isOpen())
            session.close();
        return user_photos;
    }

    @Override
    public void deleteUserPhoto(UserPhoto user_photo) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(user_photo);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();

    }
}
