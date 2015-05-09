package com.vraenchike.Model;

import com.vraenchike.Services.JSON.JSONable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
public class Banned implements JSONable {



    @Id
    @Column (name = "idBanned")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdBanned() {
        return idBanned;
    }
    public void setIdBanned(int idBanned) {
        this.idBanned = idBanned;
    }

    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Column(name = "linkType")
    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    private int idBanned;
    private String link = " ";
    private String linkType = " ";
    private User user = new User();
    private int idUser;




    @Column (name = "idUser",insertable=false,updatable=false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Banned banned = (Banned) o;

        if (idBanned != banned.idBanned) return false;
        if (idUser != banned.idUser) return false;
        if (link != null ? !link.equals(banned.link) : banned.link != null) return false;
        if (linkType != null ? !linkType.equals(banned.linkType) : banned.linkType != null) return false;
        if (user != null ? !user.equals(banned.user) : banned.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBanned;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (linkType != null ? linkType.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + idUser;
        return result;
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("link",this.getLink());
        //object.put("type",this.getLinkType());
        object.put("id",this.getIdBanned());

        return object;
    }
}
