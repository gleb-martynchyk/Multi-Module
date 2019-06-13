package org.jazzteam.martynchyk.dao;

import java.util.List;

public interface BaseDao<T> {
    T create(T object);

    T find(long id);

    List<T> findAll();

    T update(T object);

    void delete(long id);
}
