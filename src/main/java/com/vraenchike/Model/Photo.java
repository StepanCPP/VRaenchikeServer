package com.vraenchike.Model;

/**
 * Created by Alexeev on 10-Apr-15.
 */

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {
    private int id;
    private String url;
    private int likes=0;
    private int dislikes=0;



    @Id
    @Column(name = "idPhoto")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "Likes")
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Column(name = "Dislike")
    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
