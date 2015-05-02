package com.vraenchike.Model;

import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artyom on 15.04.2015.
 */
@Entity
public class Place {

    public Place(double lng, double lat, int radius, String name) {
        this.lng = lng;
        this.lat = lat;
        this.radius = radius;
        this.name = name;
    }

    public Place() {
    }

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

    @Override
    public String toString() {
        return this.name;
    }

    @Column (name = "place_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public JSONObject toJSONObject() throws JSONException {
        org.json.JSONObject jo = new JSONObject();
        jo.put("idPlace",id);
        jo.put("lng",lng);
        jo.put("lat",lat);
        jo.put("place_name",name);
        return jo;

    }

    private Set<User> users = new HashSet<>();
    private double lng;
    private double lat;
    private int radius;
    private int id;
    private String name;
}
