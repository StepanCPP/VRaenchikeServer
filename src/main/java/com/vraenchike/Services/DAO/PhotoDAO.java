package com.vraenchike.Services.DAO;

import com.vraenchike.Model.Photo;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Artyom on 15.04.2015.
 */
public interface PhotoDAO {
    public void addPhoto(Photo photo) throws SQLException;
    public void updatePhoto(Long photo_id, Photo photo) throws SQLException;
    public Photo getPhotoById(Long photo_id) throws SQLException;
    public Collection getAllPhotos() throws SQLException;
    public void deletePhoto(Photo photo) throws SQLException;
    public Photo getByURL(String url) throws  SQLException;
}
