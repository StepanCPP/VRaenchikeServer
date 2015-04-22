package com.vraenchike.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column (name = "idPlace")
    public int getIdPlace() {
        return idPlace;
    }
    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }
    private int idUserPlaces;
    private int idUser;
    private int idPlace ;
    //mapping privates

    private Set<User> users = new HashSet<>();


}
