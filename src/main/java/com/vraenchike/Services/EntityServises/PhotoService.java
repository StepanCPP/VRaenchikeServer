package com.vraenchike.Services.EntityServises;

import com.vraenchike.Exception.PhotoAlreadyAddedeException;
import com.vraenchike.Exception.PhotoAlreadyDisliked;
import com.vraenchike.Exception.PhotoAlreadyLiked;
import com.vraenchike.Exception.UserNotAuth;
import com.vraenchike.Model.Likes;
import com.vraenchike.Model.Photo;
import com.vraenchike.Model.User;
import com.vraenchike.Services.DAO.DAOFactory;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Artyom on 23.04.2015.
 */
public class PhotoService {
    public Collection GetFavoritePhotos( int offset,int count) throws SQLException, UserNotAuth {
            int counter = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = UserService.getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            throw new UserNotAuth();
        }



        TreeSet<Photo> favoritePhoto = user.getFavoritePhoto(offset, count);
        session.close();
        return favoritePhoto;
    }


    public boolean UnDislikeLike(String url,String credential,boolean isUser) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String querystr = "delete Likes where credentials=:credential " +
                "and isuser=:isuser and idtarget=:idtarger";


        Photo p = null;
        p = DAOFactory.getInstance().getPhotoDAO().getByURL(url,session);
        if(p==null){
           return false;
        }

        Query query = session.createQuery(querystr);
        query.setParameter("credential",credential);
        query.setParameter("isuser",isUser);
        query.setParameter("idtarger",p.getId());
        return query.executeUpdate()>0;
    }

    public Photo LikePhoto(String url,String idApi,String ApiType,String credential,boolean isUser) throws SQLException, PhotoAlreadyLiked, UserNotAuth, PhotoAlreadyAddedeException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Photo p = null;
        p = DAOFactory.getInstance().getPhotoDAO().getByURL(url,session);
        if(p==null){
            p = new Photo(url,idApi,ApiType,0,0);
            session.save(p);
        }


        String querystr = "from Likes where credentials=:credential " +
                "and isuser=:isuser and idtarget=:idtarger";
        Query query = session.createQuery(querystr);
        query.setParameter("credential",credential);
        query.setParameter("isuser",isUser);
        query.setParameter("idtarger",p.getId());
        Object o = query.uniqueResult();
        if(o!=null && ((Likes)o).isIslike())
        {
            throw  new PhotoAlreadyLiked();
        }



        Likes photoLike = new Likes();

        if(o==null){
            photoLike.setCredentials(credential);
            photoLike.setIsuser(isUser);
            photoLike.setIdtarget(p.getId());
        }else{
            photoLike = (Likes) o;
        }

        photoLike.setIslike(true);




        int currentLikes = p.getLikes();
        p.setLikes(currentLikes+1);

        session.save(p);
        session.save(photoLike);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
        new UserService().AddLikePhoto(url,idApi,ApiType);
        return p;

    }
    public Photo DislikePhoto(String url,String idApi,String ApiType,String credential,boolean isUser) throws SQLException, PhotoAlreadyDisliked, UserNotAuth, PhotoAlreadyAddedeException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Photo p = null;
        p = DAOFactory.getInstance().getPhotoDAO().getByURL(url,session);
        if(p==null){
            p = new Photo(url,idApi,ApiType,0,0);
            session.save(p);
        }


        String querystr = "from Likes where credentials=:credential " +
                "and isuser=:isuser and idtarget=:idtarger";
        Query query = session.createQuery(querystr);
        query.setParameter("credential",credential);
        query.setParameter("isuser",isUser);
        query.setParameter("idtarger",p.getId());
        Object o = query.uniqueResult();
        if(o!=null && !((Likes)o).isIslike())
        {
            throw  new PhotoAlreadyDisliked();
        }



        Likes photoLike = new Likes();

        if(o==null){
            photoLike.setCredentials(credential);
            photoLike.setIsuser(isUser);
        }else{
            photoLike = (Likes) o;
        }

        photoLike.setIslike(false);




        int currentLikes = p.getLikes();
        if(currentLikes>0)
            p.setLikes(currentLikes-1);

        session.save(p);
        session.save(photoLike);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
        new UserService().AddLikePhoto(url,idApi,ApiType);
        return p;

    }


}
