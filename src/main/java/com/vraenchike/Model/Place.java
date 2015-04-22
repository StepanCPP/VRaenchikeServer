package com.vraenchike.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
public class Place {



    @Column(name="lng")
    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    @Column(name="lat")
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Column(name="radius")
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


    @Id
    @Column(name = "idPlace")
    @GeneratedValue(strategy = GenerationType.AUTO)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "userplaces",  joinColumns = {
            @JoinColumn(name = "idPlace", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "idUSer",
                    nullable = false, updatable = false) })
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    private Set<User> users = new HashSet<>();
    private double lng;
    private double lat;
    private int radius;
    private int id;
}
