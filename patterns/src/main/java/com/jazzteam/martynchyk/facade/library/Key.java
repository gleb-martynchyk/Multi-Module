package com.jazzteam.martynchyk.facade.library;

public class Key {
    private int size;
    private int key;

    public int generateKey() {
        key = (int) Math.random() * size;
        return key;
    }
}
