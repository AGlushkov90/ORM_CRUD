package com.glushkov.dbcrud.repository;

import com.glushkov.dbcrud.model.BaseItem;

import java.util.Collection;

public interface GenericRepository<T extends BaseItem, ID> {
    T getByID(ID id);

    Collection<T> getAll();

    boolean delete(ID id);

    T edit(T item);

    T save(T item);
}
