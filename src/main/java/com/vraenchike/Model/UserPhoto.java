package com.vraenchike.Model;

import javax.persistence.*;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
public class UserPhoto {

    @Id
    @Column (name = "idUserPhoto")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdUserPhoto() {
        return idUserPhoto;
    }

    public void setIdUserPhoto(int idUserPhoto) {
        this.idUserPhoto = idUserPhoto;
    }
    @Column (name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    @Column (name = "idPhoto")
    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    @Column (name = "type", nullable = false, columnDefinition = "undefined")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private int idUserPhoto;
    private int idUser;
    private int idPhoto;

    private String type = "hi bro"; //TODO to solvse this field 
}
