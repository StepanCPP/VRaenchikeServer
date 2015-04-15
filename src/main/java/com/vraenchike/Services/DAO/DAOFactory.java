package com.vraenchike.Services.DAO;

import com.vraenchike.Model.Photo;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

/**
 * Created by Artyom on 15.04.2015.
 */
public class DAOFactory {


    public static synchronized DAOFactory getInstance(){
        if (instance == null){

            instance = new DAOFactory();

        }
        return instance;
    }
    public PlaceDAO getPlaceDAO(){
        if (placeDAO == null ){
            placeDAO = new PlaceDAOImpl();
        }
        return placeDAO;
    }
    public UserDAO getUserDAO(){
        if (userDAO == null){
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }
    public BannedDAO getBannedDAO(){
        if (bannedDAO == null){
            bannedDAO = new BannedDAOImpl();
        }
        return bannedDAO;
    }
    public UserBannedDAO getUserBannedDAO(){
        if (user_bannedDAO == null){
            user_bannedDAO = new UserBannedDAOImpl();
        }
        return user_bannedDAO;
    }

    public UserLoginInfoDAO getUserLoginInfoDAO(){
        if (user_login_infoDAO == null){
            user_login_infoDAO = new UserLoginInfoDAOImpl();
        }
        return user_login_infoDAO;
    }

    public UserPhotoDAO getUserPhotoDAO(){
        if (user_photoDAO == null){
            user_photoDAO = new UserPhotoDAOImpl();
        }
        return user_photoDAO;
    }
    public UserPlacesDAO getUserPlacesDAO(){
        if (user_placesDAO == null){
            user_placesDAO = new UserPlacesDAOImpl();
        }
        return user_placesDAO;
    }
    public PhotoDAO getPhotoDAO(){
        if (photoDAO == null){
            photoDAO = new PhotoDAOImpl();
        }
        return photoDAO;
    }


    private static DAOFactory instance = null;
    private static PlaceDAO placeDAO = null;
    private static UserDAO userDAO = null;
    private static BannedDAO bannedDAO = null;
    private static UserBannedDAO user_bannedDAO = null;
    private static UserLoginInfoDAO user_login_infoDAO = null;
    private static UserPhotoDAO user_photoDAO = null;
    private static UserPlacesDAO user_placesDAO = null;
    private static PhotoDAO photoDAO = null;



}
