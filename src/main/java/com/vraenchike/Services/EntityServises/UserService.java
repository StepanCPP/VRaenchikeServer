package com.vraenchike.Services.EntityServises;

import com.vraenchike.Model.Photo;
import com.vraenchike.Model.User;
import com.vraenchike.Services.DAO.DAOFactory;

import java.sql.SQLException;

/**
 * Created by Artyom on 29.04.2015.
 */
public class UserService {


    public User getCurrentUser(){
        return new User(); // TODO don't know what do to
    }
    public User getUserById(long id) throws SQLException {
        User user = DAOFactory.getInstance().getUserDAO().getUserById(id);
        return user;
    }
    public void AddFavoritePhoto(User u, String url) throws SQLException {
        long uid = u.getIdUser();
        User user = DAOFactory.getInstance().getUserDAO().getUserById(uid);
        Photo p = DAOFactory.getInstance().getPhotoDAO().getByURL(url);
        user.getPhotos().add(p);
        DAOFactory.getInstance().getUserDAO().updateUser(uid,user);
    }
    public void RemoveFavoritePhoto(User u, String url) throws SQLException {
        long uid = u.getIdUser();
        User user = DAOFactory.getInstance().getUserDAO().getUserById(uid);
        Photo p = DAOFactory.getInstance().getPhotoDAO().getByURL(url);
        user.getPhotos().remove(p);
        DAOFactory.getInstance().getUserDAO().updateUser(uid,user);

    }
}
