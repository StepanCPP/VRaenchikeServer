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
    @Column (name = "IdUser")
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

    private int idUserBanned;
    private int idUser;
    private int idBanned;

}
