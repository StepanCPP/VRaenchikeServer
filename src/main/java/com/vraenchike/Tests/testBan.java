package com.vraenchike.Tests;

import com.vraenchike.Exception.UserAlreadyBannedException;
import com.vraenchike.Exception.UserCredentialAlreadyExist;
import com.vraenchike.Exception.UserNotAuth;
import com.vraenchike.Model.Banned;
import com.vraenchike.Model.User;
import com.vraenchike.Services.EntityServises.UserService;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Alexeev on 03-May-15.
 */
public class testBan extends Assert
{
    UserService userService = new UserService();
    String u = "albert";
    String login = "wassya";
    String pass = "qweasdzxc";
    public String getRandomString()
    {
        String s = "";
        Random r = new Random();
        String letters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

        for(int i=0;i<15;i++)
        {
            s+=letters.charAt(r.nextInt(letters.length()));
        }
        return s;
    }
    public testBan()
    {
        this.u = getRandomString();
        this.login = getRandomString();
        this.pass = getRandomString();
    }
    @Test
    public void testBanDisban()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        User user;
        try {
            user = new UserService().registerUser(u,login,pass,session);
        } catch (UserCredentialAlreadyExist userCredentialAlreadyExist) {
            fail("USER ALREADY EXIST?");
        }
        session.close();
        ArrayList<Banned> banneds = new ArrayList<>();
        int count = new Random().nextInt(30)+10;

        for(int i=0;i<count;i++)
        {
            try {
               Banned b= userService.BanVk(getRandomString());
                assertNotNull(b);
                banneds.add(b);
            } catch (SQLException e) {
                fail(e.getMessage());
            } catch (UserNotAuth userNotAuth) {
                fail(userNotAuth.getMessage());
            } catch (UserAlreadyBannedException e) {
                e.printStackTrace();
            }
        }
         session = HibernateUtil.getSessionFactory().openSession();

        for(Banned b : banneds){

            try {
                assertTrue(UserService.getCurrentUser(session).getBanned().contains(b));
            } catch (SQLException e) {
                fail(e.getMessage());
            }
        }
        session.close();
    }


}
