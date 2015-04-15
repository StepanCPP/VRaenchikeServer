package com.vraenchike.Model;

import javax.persistence.*;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
public class UserBanned {

    @Id
    @Column (name = "idUserBanned")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdUserBanned() {
        return idUserBanned;
    }

    public void setIdUserBanned(int idUserBanned) {
        this.idUserBanned = idUserBanned;
    }
    @Column (name = "getIdUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    @Column (name = "idBanned")
    public int getIdBanned() {
        return idBanned;
    }

    public void setIdBanned(int idBanned) {
        this.idBanned = idBanned;
    }
    @Column (name = "User_idUser")
    public int getUser_idUser() {
        return User_idUser;
    }

    public void setUser_idUser(int user_idUser) {
        User_idUser = user_idUser;
    }
    @Column (name = "Banned_idBanned")
    public int getBanned_idBanned() {
        return Banned_idBanned;
    }

    public void setBanned_idBanned(int banned_idBanned) {
        Banned_idBanned = banned_idBanned;
    }

    private int idUserBanned;
    private int idUser;
    private int idBanned;
    private int User_idUser;
    private int Banned_idBanned;
}
