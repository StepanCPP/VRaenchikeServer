package com.vraenchike.Services.DAO;

import com.vraenchike.Model.UserPhoto;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Artyom on 15.04.2015.
 */
public interface UserPhotoDAO {
    public void addUserPhoto(UserPhoto user_photo) throws SQLException;
    public void updateUserPhoto(Long user_photo_id, UserPhoto user_photo) throws SQLException;
    public UserPhoto getUserPhotoById(Long user_photo_id) throws SQLException;
    public Collection getAllUserPhotos() throws SQLException;
    public void deleteUserPhoto(UserPhoto user_photo) throws SQLException;
}
