package com.glushkov.ormcrud.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Writer extends BaseItem {
    private String firstName;

    private String lastName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "Writer_Post",
            joinColumns = {@JoinColumn(name = "Writer_id")},
            inverseJoinColumns = {@JoinColumn(name = "Post_id")})
    @Fetch(FetchMode.SUBSELECT)
    private List<Post> posts;

    public Writer() {
        super();
    }

    public Writer(long id) {
        super(id);
    }

    public Writer(long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Writer(long id, String firstName, String lastName, List<Post> posts) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
    }

    public Writer(String firstName, String lastName, List<Post> posts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
    }

    public Writer(long id, Date created, Date updated, Status status, String firstName, String lastName, List<Post> posts) {
        super(id, created, updated, status);
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
    }

    public Writer(long id, Date updated, Status status, String firstName, String lastName, List<Post> posts) {
        super(id, updated, status);
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "Writer{" + super.toString() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", posts=" + posts +
                "}\n";
    }
}
