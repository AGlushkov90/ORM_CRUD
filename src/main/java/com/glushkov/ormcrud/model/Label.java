package com.glushkov.ormcrud.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Label extends BaseItem {
    private String name;

    @ManyToMany(mappedBy = "labels")
//@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//@JoinTable(
//        name = "Post_Label",
//        joinColumns = {@JoinColumn(name = "Label_id")},
//        inverseJoinColumns = {@JoinColumn(name = "Post_id")})
//@Fetch(FetchMode.SUBSELECT)
    private List<Post> posts;


    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }



    public Label(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Label(long id, String name) {
        super(id);
        this.name = name;
    }

    public Label(String name) {
        this.name = name;
    }

    public Label(long id, Date created, Date updated, Status status, String name) {
        super(id, created, updated, status);
        this.name = name;
    }

    public Label(long id, Date updated, Status status, String name) {
        super(id, updated, status);
        this.name = name;
    }

    public Label() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Label{" + super.toString() +
                "name='" + name + '\'' +
                "}\n";
    }}

//    @PreRemove
//    private void removeLabelsFromPosts() {
//        for (Post u : posts) {
//            u.getLabels().remove(this);
//        }
//    }
//}
