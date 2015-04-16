package com.vraenchike.Model;

import javax.persistence.*;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
public class UserLoginInfo {

    @Id
    @Column (name = "idUserLoginInfo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdUserLoginInfo() {
        return idUserLoginInfo;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIdUserLoginInfo(int idUserLoginInfo) {
        this.idUserLoginInfo = idUserLoginInfo;
    }
    @Column (name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    @Column (name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    @Column (name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    @Column (name = "User_idUser")
    public int getUser_idUser() {
        return User_idUser;
    }

    public void setUser_idUser(int user_idUser) {
        User_idUser = user_idUser;
    }

    private User user;
    private int idUserLoginInfo;
    private int idUser;
    private String login = " ";
    private String pass = " ";
    private int  User_idUser;

}
