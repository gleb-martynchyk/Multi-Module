package com.jazzteam.martynchyk.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return capacity == car.capacity &&
                Float.compare(car.speed, speed) == 0 &&
                name.equals(car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, speed);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", speed=" + speed +
                '}';
    }
}
