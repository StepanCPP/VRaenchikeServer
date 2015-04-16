package com.vraenchike.Model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
@Table(name = "userlogininfo")
public class UserLoginInfo implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLoginInfo that = (UserLoginInfo) o;


        if (idUser != that.idUser) return false;
        if (idUserLoginInfo != that.idUserLoginInfo) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + idUserLoginInfo;
        result = 31 * result + idUser;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);

        return result;
    }


    @Id
    @Column(name="idUserLoginInfo", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="user"))
    public int getIdUserLoginInfo() {
        return idUserLoginInfo;
    }



    @OneToOne
    @PrimaryKeyJoinColumn
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



    @Column (name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }







    private User user;
    private int idUserLoginInfo;
    private int idUser;
    private String login = " ";
    private String pass = " ";


}
