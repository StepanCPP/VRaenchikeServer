package com.vraenchike.Services.EntityServises;

import com.vraenchike.Exception.*;
import com.vraenchike.Model.*;
import com.vraenchike.Services.DAO.DAOFactory;
import com.vraenchike.Util.HibernateUtil;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Artyom on 29.04.2015.
 */
public class UserService {
    public  static String lastRegisterUserLogin = null;
    public User registerUser(String username,String login,String pass,Session session) throws UserCredentialAlreadyExist {

        String hql = "from User where userLoginInfo.login = :login_name";
        Query query = session.createQuery(hql);
        query.setParameter("login_name",login);

        if(query.list().size()>0){
            throw new UserCredentialAlreadyExist("login `"+login+"` already exist!");
        }

        session.beginTransaction();
        User u = new User();
        u.setUser_name(username);
        UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setLogin(login);
        userLoginInfo.setPass(pass);

        userLoginInfo.setUser(u);
        u.setUserLoginInfo(userLoginInfo);
        session.save(u);
        session.getTransaction().commit();
        lastRegisterUserLogin  = u.getUserLoginInfo().getLogin();


        return u;

    }
    public static User getCurrentUser(Session session) throws SQLException {
        //ONLY FOR TEST

        //        String login = SecurityContextHolder.getContext().getAuthentication().getName();

       // String login = lastRegisterUserLogin;
        String login =  SecurityContextHolder.getContext().getAuthentication().getName();
//        SecurityContextHolder.getContext().getAuthentication().getAuthorities();
       String hql = "from User where userLoginInfo.login = :login_name";
        Query query = session.createQuery(hql);
        query.setParameter("login_name",login);


        User u  = null;
        List list = query.list();
        if(list.size()>0){
            u = (User) list.get(0);
        }


        return u;

    }

    public Photo AddFavoritePhoto( String url) throws SQLException, UserNotAuth {
       return AddPhoto(url,"f");
    }
    public Photo RemoveFavoritePhoto(String url) throws SQLException, UserNotAuth, PhotoNotFoundException {
       return RemovePhoto(url,"f");

    }

    public void AddLikePhoto(String url) throws SQLException, UserNotAuth {
        AddPhoto(url,"l");
    }
    public void RemoveLikePhoto( String url) throws SQLException, UserNotAuth, PhotoNotFoundException {
        RemovePhoto(url,"l");
    }

    private Photo AddPhoto(String url,String type) throws SQLException, UserNotAuth {
        Session session = HibernateUtil.getSessionFactory().openSession();

        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            throw new UserNotAuth();
        }

        session.beginTransaction();

        Criteria criteria = session.createCriteria(Photo.class);
        Photo p = (Photo)criteria.add(Restrictions.eq("url", url))
                .uniqueResult();

        if (p == null){
            p = new Photo(url,0,0);
        }

        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setPhoto(p);
        userPhoto.setType(type);
        userPhoto.setUser(user);
        user.getUserPhoto().add(userPhoto);
        // p.getUsers().add(user);

        session.save(user);
        session.save(p);
        session.getTransaction().commit();
        if (session != null && session.isOpen())
            session.close();

        return p;

    }
    private Photo RemovePhoto(String url,String type) throws SQLException, PhotoNotFoundException, UserNotAuth {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
           throw new UserNotAuth();
        }


        session.beginTransaction();

        Photo toReturn = null;
        Iterator<UserPhoto> it = user.getUserPhoto().iterator();
        while(it.hasNext()){
            UserPhoto us = it.next();
            if(us.getPhoto().getUrl().equals(url) && Objects.equals(us.getType(), "f")){
                //it.remove();
                us.setDeleted(1);
                session.save(us);
                toReturn = us.getPhoto();
            }
        }
        // user.getUserPhoto().clear();



        //session.save(user);
        session.getTransaction().commit();
        //DAOFactory.getInstance().getUserDAO().updateUser(uid,user,session);


        if (session != null && session.isOpen())
            session.close();
        if(toReturn!=null){
            return toReturn;
        }
        throw new PhotoNotFoundException();
    }





    public void AddFavoritePlace( long place_id) throws SQLException {
        Session session =  HibernateUtil.getSessionFactory().openSession();
        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            return;
        }

        long pid = place_id;

        Place p = DAOFactory.getInstance().getPlaceDAO().getPlaceById(pid,session);
        if (p == null){
            DAOFactory.getInstance().getPlaceDAO().addPlace(p,session);
        }

        user.getPlaces().add(p);
        session.save(user);

        if (session != null && session.isOpen())
            session.close();

    }
    public void RemoveFavoritePlace(long place_id) throws SQLException {
        long pid = place_id;

        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            return;
        }

        Place p = DAOFactory.getInstance().getPlaceDAO().getPlaceById(pid,session);
        user.getPlaces().remove(p);
        session.save(user);
        if (session != null && session.isOpen())
            session.close();


    }
    public Banned BanInstagram(String link) throws UserAlreadyBannedException, SQLException, UserNotAuth {
        return Ban(link,"insta");
    }

    public Banned BanVk(String link) throws UserAlreadyBannedException, SQLException, UserNotAuth {
        return Ban(link,"vk");
    }

    private Banned Ban(String link,String linkType) throws SQLException, UserNotAuth, UserAlreadyBannedException {
        Session session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            throw new UserNotAuth();
        }

        //Query query = session.createQuery("from Banned where linkInsta=:insta and linkVk=:vk and user.id=:user_id");

        SQLQuery query = session.createSQLQuery("select COUNT(*) as count from banned where link=:link1 AND linkType=:type and idUser=:idUser");

        query.setParameter("link1",link);
        query.setParameter("type",linkType);
        query.setParameter("idUser",user.getIdUser());
        Object o = query.uniqueResult();



        if(!query.uniqueResult().equals(new BigInteger("0"))){
            throw new UserAlreadyBannedException();
        }


        Banned banned = new Banned();
        banned.setLink(link);
        banned.setLinkType(linkType);
        banned.setUser(user);
        user.getBanned().add(banned);
        session.save(banned);
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return banned;

    }
    public Banned DisBan(int idBanned) throws SQLException, UserNotAuth, BannedNotFoundException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            throw new UserNotAuth();
        }
        Banned toReturn = null;
        Iterator<Banned> iterator = user.getBanned().iterator();
        while (iterator.hasNext()) {
            Banned element = iterator.next();
            if (element.getIdBanned() == idBanned) {
                iterator.remove();
                toReturn = element;
            }
        }
        session.save(user);
        session.getTransaction().commit();
        session.close();

        if(toReturn==null){
            throw new BannedNotFoundException();
        }
        return toReturn;
    }
    public Collection getAllBanned() throws SQLException, UserNotAuth {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = UserService.getCurrentUser(session);
        if(user==null){
            if (session != null && session.isOpen())
                session.close();
            throw new UserNotAuth();
        }
        //OPTIMIZE
        Query query = session.createQuery("from Banned where idUser=:idUser");

        query.setParameter("idUser", user.getIdUser());
        List list = query.list();
        session.close();
        return list;
    }

}
