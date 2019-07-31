package com.segware.postings.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private final String title;
    private final String comments;
    private final int likes;
    private final int unlikes;

    public Post() {
        this.title = null;
        this.comments = null;
        this.likes = 0;
        this.unlikes = 0;
    }

    public Post(String title, String comments, int likes, int unlikes) {
        this.title = title;
        this.comments = comments;
        this.likes = likes;
        this.unlikes = unlikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getComments() {
        return comments;
    }

    public int getLikes() {
        return likes;
    }

    public int getUnlikes() {
        return unlikes;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", comments='" + comments + '\'' +
                ", likes=" + likes +
                ", unlikes=" + unlikes +
                '}';
    }
}
