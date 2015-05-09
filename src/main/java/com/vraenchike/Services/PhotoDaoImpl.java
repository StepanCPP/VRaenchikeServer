package com.vraenchike.Services;

import com.vraenchike.Model.Photo;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;

/**
 * Created by Alexeev on 10-Apr-15.
 */
public class PhotoDaoImpl extends PhotoDao{

    @Override
    public boolean createPhoto(Photo p) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(p);
        session.flush();
        session.clear();
        session.getTransaction().commit();
        return false;
    }
}
