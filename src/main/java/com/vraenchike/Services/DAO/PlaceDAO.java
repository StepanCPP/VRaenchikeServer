package com.vraenchike.Services.DAO;
import com.vraenchike.Model.Place;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Artyom on 15.04.2015.
 */
public interface PlaceDAO {
    public void addPlace(Place place) throws SQLException;
    public void updatePlace(Long Place_id, Place place) throws SQLException;
    public Place getPlaceById(Long Place_id) throws SQLException;
    public Collection getAllPlaces() throws SQLException;
    public void deletePlace(Place place) throws SQLException;

}
