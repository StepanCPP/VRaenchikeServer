package com.vraenchike.Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;

/**
 * Created by Alexeev on 09-Apr-15.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private HibernateUtil(){}


    public static SessionFactory getSessionFactory() {
        if(sessionFactory==null) {

            try {
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml").buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
