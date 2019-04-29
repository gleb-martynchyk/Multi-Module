package com.jazzteam.martynchyk.facade.library;

public class Key {
    private int size = 8;
    private int key;

    public int generateKey() {
        while (key == 0) {
            key = (int) (Math.random() * size);
        }
        return key;
    }

    public int getSize() {
        return size;
    }

    public int getKey() {
        return key;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
