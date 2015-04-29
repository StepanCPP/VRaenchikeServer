package com.vraenchike.Services.EntityServises;

import com.vraenchike.Model.Place;
import com.vraenchike.Services.DAO.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Artyom on 29.04.2015.
 */
public class PlaceService {

    public void AddPlace(int lng, int lat, int radius, String name) throws SQLException {
        Place p = new Place(lng,lat,radius,name);
        DAOFactory.getInstance().getPlaceDAO().addPlace(p);
    }
    public void RemovePlace(long id) throws SQLException {
       Place p = DAOFactory.getInstance().getPlaceDAO().getPlaceById(id);
        DAOFactory.getInstance().getPlaceDAO().deletePlace(p);
    }
   public Collection<Place> GetAllPlaces() throws SQLException {
       Collection<Place> places = DAOFactory.getInstance().getPlaceDAO().getAllPlaces();
       return places;
   }
}
