package com.vraenchike.Model;

import com.vraenchike.Services.JSON.JSONable;
import org.json.JSONException;
import org.json.JSONObject;

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
public class User implements Serializable,JSONable {

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
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
    //many ot many relationship

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "userphoto", joinColumns = {
            @JoinColumn(name = "idUser", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "idPhoto",
                    nullable = false, updatable = false) })
    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }


    public void setBanned(Set<Banned> banned) {
        this.banned = banned;
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

    private UserLoginInfo userLoginInfo;
    private String user_name = "";
    private long idUser;
    private Date lastPhotoView = new Date();
    //mapping privates
    private Set<Place> places = new HashSet<>();
    private Set<Photo> photos = new HashSet<>();
    private Set<Banned> banned = new HashSet<>();


    public JSONObject toJSONObject() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("idUSer",idUser);
        jo.put("lastPhotoView",lastPhotoView);
        jo.put("username",user_name);

        return jo;

    }
@OneToMany (fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Banned> getBanned() {
        return banned;
    }


}
