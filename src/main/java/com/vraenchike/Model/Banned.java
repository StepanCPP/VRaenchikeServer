package com.vraenchike.Model;

import com.vraenchike.Services.JSON.JSONable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
public class Banned  {

    @Column (name = "linkVK")
    public String getLinkVk() {
        return linkVk;
    }

    public void setLinkVk(String link) {
        this.linkVk = link;
    }
    @Id
    @Column (name = "idBanned")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdBanned() {
        return idBanned;
    }
    @Column (name = "linkInsta")
    public String getLinkInsta() {
        return linkInsta;
    }

    public void setLinkInsta(String linkInsta) {
        this.linkInsta = linkInsta;
    }

    public void setIdBanned(int idBanned) {
        this.idBanned = idBanned;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBanned", nullable = false, insertable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private int idBanned;
    private String linkVk = " ";
    private String linkInsta = " ";
    private User user = new User();

}
