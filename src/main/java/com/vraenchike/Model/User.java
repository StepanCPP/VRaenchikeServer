package com.vraenchike.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
public class User {

    @Column(name = "lastPhotoView", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastPhotoView() {
        return lastPhotoView;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public UserLoginInfo getUserLoginInfo() {
        return userLoginInfo;
    }

    public void setUserLoginInfo(UserLoginInfo userLoginInfo) {
        this.userLoginInfo = userLoginInfo;
    }

    public void setLastPhotoView(Date lastPhotoView) {
        this.lastPhotoView = lastPhotoView;
    }
    @Column(name = "user_name")
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Id
    @Column (name = "idUser")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    private UserLoginInfo userLoginInfo;
    private String user_name = " ";
    private int idUser;
    private Date lastPhotoView = new Date();
}
