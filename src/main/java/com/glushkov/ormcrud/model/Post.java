package com.glushkov.ormcrud.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Post extends BaseItem {
    private String title;
    private String content;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "Post_Label",
            joinColumns = {@JoinColumn(name = "Post_id")},
            inverseJoinColumns = {@JoinColumn(name = "Label_id")})
    @Fetch(FetchMode.SUBSELECT)
    private List<Label> labels;

    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "posts")
    private List<Writer> writers;

    public Post() {
        super();
    }

    public Post(long id) {
        super(id);
    }

    public Post(long id, Date created, Status status) {
        super(id, created, status);
    }

    public Post(long id, String title, String content, Date created, Date updated, Status status) {
        this(id, created, status);
        this.title = title;
        this.content = content;
    }

    public Post(long id, String title, String content, List<Label> labels) {
        super(id);
        this.title = title;
        this.content = content;
        this.labels = labels;
    }

    public Post(String title, String content, List<Label> labels) {
        this.title = title;
        this.content = content;
        this.labels = labels;
    }

    public Post(long id, Date created, Date updated, Status status, String title, String content, List<Label> labels) {
        super(id, created, updated, status);
        this.title = title;
        this.content = content;
        this.labels = labels;
    }

    public Post(long id, Date updated, Status status, String title, String content, List<Label> labels) {
        super(id, updated, status);
        this.title = title;
        this.content = content;
        this.labels = labels;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<Label> getLabels() {
        return labels;
    }


    @Override
    public String toString() {
        return "Post{" + super.toString() +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", labels=" + labels +
                "}\n";
    }
}
