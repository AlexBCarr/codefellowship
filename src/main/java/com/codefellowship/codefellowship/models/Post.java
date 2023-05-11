package com.codefellowship.codefellowship.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(columnDefinition = "text")
    String body;
    Date createdAt;

    @ManyToOne
    @JoinColumn(name = "appUser_id")
    AppUser author;

    public Post() {
        //empty
    }

    public Post(String postContent, Date createdAt) {
        this.body = postContent;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String postContent) {
        this.body = postContent;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public AppUser getAppUser() {
        return author;
    }

    public void setApplicationUser(AppUser appUser) {
        this.author = appUser;
    }
}
