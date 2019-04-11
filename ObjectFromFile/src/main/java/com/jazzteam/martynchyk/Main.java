package com.jazzteam.martynchyk;

import com.jazzteam.martynchyk.Entity.Car;

public class Main {
    public final static void main(String[] args) {
        start();
    }

    public static void start() {
        Mapper mapper = new Mapper();
        Car car = new Car("Subary", 4, 200f);
        System.out.println(mapper.objectToString(car));
        System.out.println("After");
        String carString = mapper.objectToString(car);
        Car carA = (Car) mapper.stringToObject(carString);
        System.out.println(mapper.objectToString(carA));
    }
}
