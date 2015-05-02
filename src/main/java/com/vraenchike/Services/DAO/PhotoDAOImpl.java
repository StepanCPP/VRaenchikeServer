package com.vraenchike.Services.DAO;

import com.vraenchike.Model.Photo;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Artyom on 15.04.2015.
 */
public class PhotoDAOImpl implements PhotoDAO {
    @Override
    public void addPhoto(Photo photo) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(photo);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void updatePhoto(Long photo_id, Photo photo) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(photo);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public Photo getPhotoById(Long photo_id) throws SQLException {
        Session session = null;
        Photo photo = null;
        session = HibernateUtil.getSessionFactory().openSession();
        photo = (Photo)session.load(Photo.class, photo_id);
        if (session != null && session.isOpen())
            session.close();
        return photo;
    }

    @Override
    public Collection getAllPhotos() throws SQLException {
        Session session = null;
        List photos = new ArrayList<Photo>();
        session = HibernateUtil.getSessionFactory().openSession();
        photos = session.createCriteria(Photo.class).list();
        if (session != null && session.isOpen())
            session.close();
        return photos;
    }

    @Override
    public void deletePhoto(Photo photo) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(photo);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();

    }

    public Photo getByURL(String url,Session session){
        //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Photo p = null;
        //if(!session.isOpen())
        //    session = HibernateUtil.getSessionFactory().openSession();
       // session.getSessionFactory().getCurrentSession().beginTransaction();



        Criteria criteria = session.createCriteria(Photo.class);
        p = (Photo)criteria.add(Restrictions.eq("url", url))
                .uniqueResult();
    //        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();

      //  if (session != null && session.isOpen())
       //    session.close();
        return p;

    }
}
