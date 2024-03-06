package com.glushkov.dbcrud.model;

public abstract class BaseItem {
    private long id;

    public BaseItem(long id) {
        this.id = id;
    }

    public BaseItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id + ", ";
    }
}
