package com.vraenchike.Services.DAO;

import com.vraenchike.Model.UserPlaces;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Artyom on 15.04.2015.
 */
public interface UserPlacesDAO {
    public void addUserPlaces(UserPlaces user_places_places) throws SQLException;
    public void updateUserPlaces(Long user_places_id, UserPlaces user_places) throws SQLException;
    public UserPlaces getUserPlacesById(Long user_places_id) throws SQLException;
    public Collection getAllUserPlacess() throws SQLException;
    public void deleteUserPlaces(UserPlaces user_places) throws SQLException;
}
