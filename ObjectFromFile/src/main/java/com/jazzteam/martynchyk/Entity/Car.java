package com.jazzteam.martynchyk.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Car {
    private String name;
    private int capacity;
    private float speed;

    public Car() {
    }

    public Car(String name, int capacity, float speed) {
        this.name = name;
        this.capacity = capacity;
        this.speed = speed;
    }

}
