package com.vraenchike.Model;

import javax.persistence.*;

/**
 * Created by Alexeev on 09-Apr-15.
 */
@Entity
@Table(name="test")
public class TestModel {
    private int id;
    private String key;
    private String value;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name="value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}
