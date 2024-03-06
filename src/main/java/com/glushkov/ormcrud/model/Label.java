package com.glushkov.ormcrud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Label extends BaseItem {
    private String name;

    @ManyToMany(mappedBy = "labels")
    private List<Post> posts;

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
    }
}
