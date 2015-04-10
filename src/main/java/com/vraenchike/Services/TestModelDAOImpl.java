package com.vraenchike.Services;

import com.vraenchike.Model.TestModel;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Alexeev on 09-Apr-15.
 */
public class TestModelDAOImpl extends TestModelDAO {
    @Override
    public boolean create(TestModel t) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();


        session.save(t);

        transaction.commit();


        return false;
    }
}
