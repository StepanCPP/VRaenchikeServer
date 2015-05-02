package com.vraenchike.Tests;

import com.vraenchike.Exception.UserCredentialAlreadyExist;
import com.vraenchike.Model.User;
import com.vraenchike.Services.EntityServises.UserService;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexeev on 02-May-15.
 */
public class UserServiceCredentialTests extends Assert {
    UserService service = new UserService();
    String u = "albert";
    String login = "wassya";
    String pass = "qweasdzxc";
    public void truncateUserTable()
    {
        String deleteUserInfoQ = "delete from UserLoginInfo";
        String deleteUserQ = "delete from User ";
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.createQuery(deleteUserInfoQ).executeUpdate();
        s.createQuery(deleteUserQ).executeUpdate();
        s.getTransaction().commit();
        service = new UserService();
    }
    @Test
    public void testRegister()
    {
        truncateUserTable();
        Session s =HibernateUtil.getSessionFactory().openSession();

        try {
            service.registerUser(u,login,pass,s);
        } catch (UserCredentialAlreadyExist userCredentialAlreadyExist) {
           fail("USER ALREADY EXIST?");
        }


        s.close();
    }

    @Test
    public void testAlreadyRegistered()
    {
        Session s =HibernateUtil.getSessionFactory().openSession();




        try {
            service.registerUser(u,login,pass,s);
            fail("USER ALREADY EXIST!");
        } catch (UserCredentialAlreadyExist userCredentialAlreadyExist) {
        }
        s.close();
    }

    @Test
    public void testLoginExistUser()
    {
        Session s =HibernateUtil.getSessionFactory().openSession();


            String hql = "from User where userLoginInfo.login = :login_name";
            Query query = s.createQuery(hql);
            query.setParameter("login_name",login);
            List list = query.list();
            User currentUser = null;
            if(list.size()>0){
                currentUser = (User) list.get(0);
            }


            assertNotNull(currentUser);
            assertEquals(currentUser.getUser_name(), u);
            assertEquals(currentUser.getUserLoginInfo().getLogin(),login);
            assertEquals(currentUser.getUserLoginInfo().getPass(),pass);


        s.close();
    }

    @Test
    public void testLoginNotExistUser()
    {
        Session s =HibernateUtil.getSessionFactory().openSession();


        String hql = "from User where userLoginInfo.login = :login_name";
        Query query = s.createQuery(hql);
        query.setParameter("login_name",login+"A");
        List list = query.list();
        User currentUser = null;
        if(list.size()>0){
            currentUser = (User) list.get(0);
        }

        assertNull(currentUser);


        s.close();
    }


}
