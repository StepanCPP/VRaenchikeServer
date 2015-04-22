package com.vraenchike.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
public class Banned {

    @Column (name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    @Id
    @Column (name = "idBanned")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdBanned() {
        return idBanned;
    }

    public void setIdBanned(int idBanned) {
        this.idBanned = idBanned;
    }

    //many to many relationship

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "userbanned",  joinColumns = {
            @JoinColumn(name = "idBanned", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "idUser",
                    nullable = false, updatable = false) })
    public Set<User> getUsersbanned() {
        return usersbanned;
    }

    public void setUsersbanned(Set<User> usersbanned) {
        this.usersbanned = usersbanned;
    }

    private int idBanned;
    private String link  = " ";
    private Set<User> usersbanned = new HashSet<>();

}
