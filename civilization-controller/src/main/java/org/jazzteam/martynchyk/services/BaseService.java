package org.jazzteam.martynchyk.services;

import java.util.List;

public interface BaseService<T> {
    T create(T object);

    T find(long id);

    List<T> findAll();

    T update(T object);

    void delete(long id);
}
