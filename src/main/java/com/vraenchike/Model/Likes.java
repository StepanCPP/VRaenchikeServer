package com.vraenchike.Model;

import javax.persistence.*;

/**
 * Created by Alexeev on 03-May-15.
 */
@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @Column(name = "idlikes")
    @GeneratedValue(strategy = GenerationType.AUTO)

    public int getIdlikes() {
        return idlikes;
    }

    public void setIdlikes(int idlikes) {
        this.idlikes = idlikes;
    }


    @Column(name="credential")
    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }


    @Column(name="isuser")
    public boolean isIsuser() {
        return isuser;
    }

    public void setIsuser(boolean isuser) {
        this.isuser = isuser;
    }


    @Column(name="target")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Column(name="idtarget")
    public int getIdtarget() {
        return idtarget;
    }

    public void setIdtarget(int idtarget) {
        this.idtarget = idtarget;
    }

    @Column(name="islike")
    public boolean isIslike() {
        return islike;
    }

    public void setIslike(boolean islike) {
        this.islike = islike;
    }

    private int idlikes;
    private String credentials;
    private boolean isuser=true;
    private boolean islike = true;
    private int idtarget;
    //LIKE PHOTO
    private String target="photo";



}
