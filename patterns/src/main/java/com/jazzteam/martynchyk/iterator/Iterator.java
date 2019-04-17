package com.jazzteam.martynchyk.iterator;

public interface Iterator<T> {
    boolean hasNext();

    T next();

    void remove();
}
