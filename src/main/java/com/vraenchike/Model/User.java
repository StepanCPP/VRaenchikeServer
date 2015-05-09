package com.vraenchike.Model;

import com.vraenchike.Services.JSON.JSONable;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
@Table(name="user")
public class User implements Serializable,JSONable {

    @Column(name = "lastPhotoView", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastPhotoView() {
        return lastPhotoView;
    }

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
    public UserLoginInfo getUserLoginInfo() {
        return userLoginInfo;
    }
    public void setUserLoginInfo(UserLoginInfo userLoginInfo) {
        this.userLoginInfo = userLoginInfo;
    }



    public void setLastPhotoView(Date lastPhotoView) {
        this.lastPhotoView = lastPhotoView;
    }
    @Column(name = "user_name")
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Id
    @Column (name = "idUser")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    //many ot many relationship

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.u",
            cascade = CascadeType.ALL)
    public Set <UserPhoto> getUserPhoto() {
        return usersPhoto;
    }



    public void setUserPhoto(Set <UserPhoto> users) {
        this.usersPhoto = users;
    }

    public void setBanned(Set<Banned> banned) {
        this.banned = banned;
    }



    @Transient
    private Set<Photo> getPhotos(String type,int offest,int count,boolean ordering)
    {
        int offsetCount = 0;
        Set<Photo> photos = null;
        if(ordering){
            photos =  new TreeSet<>();
        }else{
            photos = new HashSet<>();
        }
        Iterator<UserPhoto> iterator = this.getUserPhoto().iterator();
        while (iterator.hasNext() && photos.size()<count){
            UserPhoto next = iterator.next();
            if(next.getDeleted()==0 && next.getType().equals(type) && offsetCount++>=offest)
                photos.add(next.getPhoto());
        }
        return photos;
    }


    @Transient
    public Set<Photo> getFavoritePhoto(int offset,int count)
    {
        return getPhotos("f",offset,count,true);
    }
    @Transient
    public Set<Photo> getLikedPhoto(int offset,int count)
    {
        return getPhotos("l",offset,count,false);
    }
    @Transient
    public Set<Integer> getLikedPhotoIds(int offset,int count)
    {
        int offsetCount = 0;
        Set<Integer> photos = new HashSet<>();

        Iterator<UserPhoto> iterator = this.getUserPhoto().iterator();
        while (iterator.hasNext() && photos.size()<count){
            UserPhoto next = iterator.next();
            if(next.getDeleted()==0 && next.getType().equals("l") && offsetCount++>=offset)
                photos.add(next.getPhoto().getId());
        }
        return photos;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "userplaces",  joinColumns = {
            @JoinColumn(name = "idUser", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "idPlace",
                    nullable = false, updatable = false) })
    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return this.user_name;
    }

    private UserLoginInfo userLoginInfo = new UserLoginInfo();
    private String user_name = "";
    private int idUser;
    private Date lastPhotoView = new Date();
    //mapping privates
    private Set<Place> places = new HashSet<>();
    private Set <UserPhoto> usersPhoto = new TreeSet<>();
    private Set<Banned> banned = new HashSet<>();


    public JSONObject toJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("idUSer",idUser);
        jo.put("lastPhotoView",lastPhotoView);
        jo.put("username",user_name);

        return jo;

    }
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "user",orphanRemoval = true)
        public Set<Banned> getBanned() {
            return banned;
        }


}
