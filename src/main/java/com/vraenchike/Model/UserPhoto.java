package com.vraenchike.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
@Table(name = "userphoto")
@AssociationOverrides({
        @AssociationOverride(name = "pk.u",
                joinColumns = @JoinColumn(name = "idUser")),
        @AssociationOverride(name = "pk.p",
                joinColumns = @JoinColumn(name = "idPhoto")) })
public class UserPhoto implements Comparable<UserPhoto> {




    @EmbeddedId
    public UserPhotoId getPk() {
        return pk;
    }

    public void setPk(UserPhotoId pk) {
        this.pk = pk;
    }
    @Column (name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Transient
    public User getUser() {
        return getPk().getU();
    }

    public void setUser(User user) {
        getPk().setU(user);
    }

    @Transient
    public Photo getPhoto() {
        return getPk().getP();
    }

    public void setPhoto(Photo photo) {
        getPk().setP(photo);
    }



    private UserPhotoId pk = new UserPhotoId();
    private String type = "hi bro";
    private Date addedDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "added_date")
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @Override
    public int compareTo(UserPhoto o) {
        return this.addedDate.compareTo(o.getAddedDate());
    }
}
