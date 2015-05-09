package com.vraenchike.Model;

/**
 * Created by Alexeev on 10-Apr-15.
 */

import com.vraenchike.Services.JSON.JSONable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "photo")
public class Photo implements Comparable<Photo> {
    private int id;
    private String url = "";
    private int likes=0;
    private int dislikes=0;
    private String idApiServices = "";
    private String ApiServiceType = "";

    @Column(name = "ApiServiceType")
    public String getApiServiceType() {
        return ApiServiceType;
    }

    public void setApiServiceType(String apiServiceType) {
        ApiServiceType = apiServiceType;
    }

    @Column(name = "idApiServices")
    public String getIdApiServices() {
        return idApiServices;
    }

    public void setIdApiServices(String idApiServices) {
        this.idApiServices = idApiServices;
    }

    private Set <UserPhoto> usersPhoto = new TreeSet<>();

    public Photo(String url,String idApi,String ApiType, int likes, int dislikes) {
        this.url = url;
        this.likes = likes;
        this.dislikes = dislikes;
        this.setApiServiceType(ApiType);
        this.setIdApiServices(idApi);
    }

    public Photo() {
    }


    @Id
    @Column(name = "idPhoto")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="URL")
    public String getUrl(){
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "Likes")
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Column(name = "Dislike")
    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }


    /////many to many retaionship


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.p",cascade = CascadeType.ALL)
    public Set <UserPhoto> getUserPhoto() {
        return usersPhoto;
    }

    public void setUserPhoto(Set <UserPhoto> users) {
        this.usersPhoto = users;
    }

    public JSONObject toJSONObject() throws JSONException {
            org.json.JSONObject jo = new JSONObject();
            jo.put("url",url);
            jo.put("likes",likes);
            jo.put("dislikes",dislikes);
             jo.put("id",getId());
            jo.put("idApi",getIdApiServices());
            jo.put("ApiType",getApiServiceType());
            return jo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (dislikes != photo.dislikes) return false;
        if (id != photo.id) return false;
        if (likes != photo.likes) return false;
        if (url != null ? !url.equals(photo.url) : photo.url != null) return false;
        if (usersPhoto != null ? !usersPhoto.equals(photo.usersPhoto) : photo.usersPhoto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + likes;
        result = 31 * result + dislikes;
        result = 31 * result + (usersPhoto != null ? usersPhoto.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Photo o) {
        return this.url.compareTo(o.getUrl());
    }
}
