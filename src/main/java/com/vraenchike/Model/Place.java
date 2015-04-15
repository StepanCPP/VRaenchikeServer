package com.vraenchike.Model;

import javax.persistence.*;

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

    private double lng;
    private double lat;
    private int radius;
    private int id;
}
