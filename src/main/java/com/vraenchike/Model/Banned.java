package com.vraenchike.Model;

import javax.persistence.*;

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

    private int idBanned;
    private String link  = " ";
}
