package com.glushkov.ormcrud.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public abstract class BaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date created;
    private Date updated;
    private Status status;

    public BaseItem(long id) {
        this.id = id;
    }

    public BaseItem() {
    }

    public BaseItem(long id, Date created, Date updated, Status status) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.status = status;
    }

    public BaseItem(long id, Date updated, Status status) {
        this.id = id;
        this.updated = updated;
        this.status = status;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public Date getCreated() {
        return created;
    }

    public Status getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id + ", " +
                "status='" + status + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'';
    }
}
