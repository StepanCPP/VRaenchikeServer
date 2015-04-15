package com.vraenchike.Services.DAO;

import com.vraenchike.Model.UserLoginInfo;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Artyom on 15.04.2015.
 */
public class UserLoginInfoDAOImpl implements UserLoginInfoDAO {
    @Override
    public void addUserLoginInfo(UserLoginInfo user_login_info) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user_login_info);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public void updateUserLoginInfo(Long user_login_info_id, UserLoginInfo user_login_info) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user_login_info);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public UserLoginInfo getUserLoginInfoById(Long user_login_info_id) throws SQLException {
        Session session = null;
        UserLoginInfo user_login_info = null;
        session = HibernateUtil.getSessionFactory().openSession();
        user_login_info = (UserLoginInfo)session.load(UserLoginInfo.class, user_login_info_id);
        if (session != null && session.isOpen())
            session.close();
        return user_login_info;
    }

    @Override
    public Collection getAllUserLoginInfos() throws SQLException {
        Session session = null;
        List user_login_infos = new ArrayList<UserLoginInfo>();
        session = HibernateUtil.getSessionFactory().openSession();
        user_login_infos = session.createCriteria(UserLoginInfo.class).list();
        if (session != null && session.isOpen())
            session.close();
        return user_login_infos;
    }

    @Override
    public void deleteUserLoginInfo(UserLoginInfo user_login_info) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(user_login_info);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();

    }
}
