package com.vraenchike.Services.EntityServises;

import com.vraenchike.Model.Photo;
import com.vraenchike.Services.DAO.DAOFactory;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Artyom on 23.04.2015.
 */
public class PhotoService {
    public Collection GetAllPhotos( int count) throws SQLException {
            int counter = 0;
        Collection<Photo> toReturn = new ArrayList<Photo>();
        for(Object p : DAOFactory.getInstance().getPhotoDAO().getAllPhotos()){
            if(counter >= count)
                return toReturn;


            toReturn.add((Photo)p);
        }
        return toReturn;
    }

    public Photo LikePhoto(String url) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Photo p = null;
        p = DAOFactory.getInstance().getPhotoDAO().getByURL(url,session);
        int currentLikes = p.getLikes();
        p.setLikes(currentLikes+1);
        DAOFactory.getInstance().getPhotoDAO().updatePhoto((long)p.getId(),p);
        return p;

    }
    public Photo DislikePhoto(String url) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Photo p = null;
        p = DAOFactory.getInstance().getPhotoDAO().getByURL(url,session);
        int currentDislikes = p.getDislikes();
        p.setLikes(currentDislikes + 1);
        DAOFactory.getInstance().getPhotoDAO().updatePhoto((long)p.getId(),p);
        return p;

    }


}
