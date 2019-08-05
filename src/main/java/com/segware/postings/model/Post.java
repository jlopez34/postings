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
    private String title;
    private String comments;
    private int likes;
    private int unlikes;

    public Post() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getUnlikes() {
        return unlikes;
    }

    public void setUnlikes(int unlikes) {
        this.unlikes = unlikes;
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
