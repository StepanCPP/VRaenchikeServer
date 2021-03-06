package com.vraenchike.Services.EntityServises;

import com.vraenchike.Exception.*;
import com.vraenchike.Model.Likes;
import com.vraenchike.Model.Photo;
import com.vraenchike.Model.User;
import com.vraenchike.Services.DAO.DAOFactory;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by Artyom on 23.04.2015.
 */
public class PhotoService {
    public JSONArray GetFavoritePhotos( int offset,int count) throws SQLException, UserNotAuth, JSONException {
            int counter = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = UserService.getCurrentUser(session);


        if(user==null){


            if (session != null && session.isOpen())
                session.close();
            throw new UserNotAuth();
        }



        Set<Photo> favoritePhoto = user.getFavoritePhoto(offset, count);



        Iterator<Photo> iterator = favoritePhoto.iterator();
        JSONArray array = new JSONArray();
        while (iterator.hasNext()){
            array.put(iterator.next().toJSONObject());
        }

        session.close();
        return array;
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

    public Photo LikePhoto(String url,String idApi,String ApiType,String credential,boolean isUser) throws SQLException,  UserNotAuth, PhotoAlreadyAddedeException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = UserService.getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            throw new UserNotAuth();
        }
        Query query = session.createQuery("from Photo where idApiServices=:idapi and apiServiceType=:apitype");
        query.setParameter("apitype",ApiType);
        query.setParameter("idapi",idApi);
        Photo p = (Photo) query.uniqueResult();
        if (p == null){
            p = new Photo(url,idApi,ApiType,0,0);
            session.save(p);
        }


        String querystr = "from Likes where credentials=:credential " +
                "and isuser=:isuser and idtarget=:idtarger";
        query = session.createQuery(querystr);
        query.setParameter("credential",credential);
        query.setParameter("isuser",isUser);
        query.setParameter("idtarger",p.getId());
        Object o = query.uniqueResult();
        if(o!=null && ((Likes)o).isIslike())
        {
            throw  new PhotoAlreadyAddedeException();
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
        if(!user.getPhoto().contains(p)){
            user.getPhoto().add(p);

            session.save(user);
        }


        session.save(photoLike);
        session.save(p);

        session.flush();
        session.getTransaction().commit();
        session.flush();

        if (session != null && session.isOpen())
            session.close();
         return p;

    }
    public Photo DislikePhoto(String url,String idApi,String ApiType,String credential,boolean isUser) throws SQLException, PhotoAlreadyDisliked, UserNotAuth, PhotoAlreadyAddedeException, PhotoNotFoundException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = UserService.getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            throw new UserNotAuth();
        }
        Query query = session.createQuery("from Photo where idApiServices=:idapi and apiServiceType=:apitype");
        query.setParameter("apitype",ApiType);
        query.setParameter("idapi",idApi);
        Photo p = (Photo) query.uniqueResult();
        if (p == null){
            throw new PhotoNotFoundException();
        }

        String querystr = "from Likes where credentials=:credential " +
                "and isuser=:isuser and idtarget=:idtarger";
        query = session.createQuery(querystr);
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


        user.getPhoto().remove(p);
        session.save(photoLike);
        session.save(p);
        session.flush();
        session.getTransaction().commit();
        session.flush();

        if (session != null && session.isOpen())
            session.close();
        return p;

    }


}
