package com.vraenchike.Services.EntityServises;

import com.vraenchike.Model.Banned;
import com.vraenchike.Model.Photo;
import com.vraenchike.Model.Place;
import com.vraenchike.Model.User;
import com.vraenchike.Services.DAO.DAOFactory;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.sql.SQLException;

/**
 * Created by Artyom on 29.04.2015.
 */
public class UserService {


    public User getCurrentUser() throws SQLException {
        return this.getUserById(1); // TODO don't know what do to
    }
    public User getUserById(long id) throws SQLException {
        User user = DAOFactory.getInstance().getUserDAO().getUserById(id);
        return user;
    }
    public void AddFavoritePhoto(long user_id, String url) throws SQLException {
        long uid = user_id;
        User user = DAOFactory.getInstance().getUserDAO().getUserById(uid);
        Photo p = DAOFactory.getInstance().getPhotoDAO().getByURL(url);
        if (p == null){
            p = new Photo(url,0,0);
            DAOFactory.getInstance().getPhotoDAO().addPhoto(p);
        }
         //  Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        // s.beginTransaction();
           user.getPhotos().add(p);

        DAOFactory.getInstance().getUserDAO().updateUser(uid,user);
    }
    public void RemoveFavoritePhoto(long id, String url) throws SQLException {
        long uid = id;
        User user = DAOFactory.getInstance().getUserDAO().getUserById(uid);
        Photo p = DAOFactory.getInstance().getPhotoDAO().getByURL(url);
        user.getPhotos().remove(p);
        DAOFactory.getInstance().getUserDAO().updateUser(uid,user);
    }
    public void AddFavoritePlace(long user_id, long place_id) throws SQLException {
        long pid = user_id;
        long uid = place_id;
        Place p = DAOFactory.getInstance().getPlaceDAO().getPlaceById(pid);
        if (p == null){
            DAOFactory.getInstance().getPlaceDAO().addPlace(p);
        }
        User u = DAOFactory.getInstance().getUserDAO().getUserById(uid);
        u.getPlaces().add(p);
        DAOFactory.getInstance().getUserDAO().updateUser(uid,u);
    }
    public void RemoveFavoritePlace(long user_id, long place_id) throws SQLException {
        long pid = user_id;
        long uid = place_id;
        Place p = DAOFactory.getInstance().getPlaceDAO().getPlaceById(pid);
        User u = DAOFactory.getInstance().getUserDAO().getUserById(uid);
        u.getPlaces().remove(p);
        DAOFactory.getInstance().getUserDAO().updateUser(uid,u);
    }
    public void Ban(User user, Banned banned) throws SQLException {
        long uid = user.getIdUser();
        long buid = banned.getIdBanned();
        User u = DAOFactory.getInstance().getUserDAO().getUserById(uid);
        u.getBanned().add(banned);
        DAOFactory.getInstance().getUserDAO().updateUser(uid,u);
    }
    public void DisBan(User user, Banned banned) throws SQLException {
        long uid = user.getIdUser();
        long buid = banned.getIdBanned();
        User u = DAOFactory.getInstance().getUserDAO().getUserById(uid);
        u.getBanned().remove(banned);
        DAOFactory.getInstance().getUserDAO().updateUser(uid,u);
    }
}
