package com.vraenchike.Model;

import javax.persistence.*;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
public class UserPlaces {

    @Id
    @Column (name = "idUserPlaces")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdUserPlaces() {
        return idUserPlaces;
    }

    public void setIdUserPlaces(int idUserPlaces) {
        this.idUserPlaces = idUserPlaces;
    }
    @Column (name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    @Column (name = "Place_idPlace")
    public int getPlace_idPlace() {
        return Place_idPlace;
    }

    public void setPlace_idPlace(int place_idPlace) {
        Place_idPlace = place_idPlace;
    }
    @Column (name = "User_idUser")
    public int getUser_idUser() {
        return User_idUser;
    }

    public void setUser_idUser(int user_idUser) {
        User_idUser = user_idUser;
    }
    @Column (name = "idPlace")
    public String getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(String idPlace) {
        this.idPlace = idPlace;
    }

    private int idUserPlaces;
    private int idUser;
    // is that a valid type? does string can be an id? TODO
    private String idPlace = " " ;
    private int User_idUser;
    private int Place_idPlace;

}
