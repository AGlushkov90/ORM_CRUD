package com.glushkov.dbcrud.model;

public enum Status {
    ACTIVE(1), UNDER_REVIEW(2), DELETED(3);

    private final int id;

    Status(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Status getById(int id) {
        Status[] array = Status.values();
        for (Status status : array) {
            if (status.id == id) {
                return status;
            }
        }
        return null;
    }
}
