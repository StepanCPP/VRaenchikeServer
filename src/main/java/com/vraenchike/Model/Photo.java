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

@Entity
@Table(name = "photo")
public class Photo  {
    private int id;
    private String url = " ";
    private int likes=0;
    private int dislikes=0;
    private Set <User> users = new HashSet<User>();



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


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "userphoto", joinColumns = {
            @JoinColumn(name = "idPhoto", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "idUSer",
                    nullable = false, updatable = false) })
    public Set <User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (id != photo.id) return false;
        if (likes != photo.likes) return false;
        if (dislikes != photo.dislikes) return false;
        if (url != null ? !url.equals(photo.url) : photo.url != null) return false;
        return !(users != null ? !users.equals(photo.users) : photo.users != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + likes;
        result = 31 * result + dislikes;
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    public JSONObject toJSONObject() throws JSONException {
            org.json.JSONObject jo = new JSONObject();
            jo.put("url",url);
            jo.put("likes",likes);
            jo.put("dislikes",dislikes);
            return jo;
    }
}
