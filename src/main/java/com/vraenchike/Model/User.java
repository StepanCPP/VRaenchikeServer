package com.vraenchike.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
@Table(name="user")
public class User implements Serializable {

    @Column(name = "lastPhotoView", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastPhotoView() {
        return lastPhotoView;
    }

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
        public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }
    @ManyToMany(fetch =  FetchType.LAZY, mappedBy = "usersbanned")
    public Set <Banned> getBanned() {
        return banned;
    }

    public void setBanned(Set<Banned> banned) {
        this.banned = banned;
    }
    @ManyToMany(fetch =  FetchType.LAZY, mappedBy = "users")
    public Set<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    private UserLoginInfo userLoginInfo;
    private String user_name = "";
    private int idUser;
    private Date lastPhotoView = new Date();
    //mapping privates
    private Set<Place> places = new HashSet<>();
    private Set<Photo> photos = new HashSet<>();
    private Set<Banned> banned = new HashSet<>();




}
