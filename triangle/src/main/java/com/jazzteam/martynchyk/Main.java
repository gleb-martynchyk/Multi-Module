package com.jazzteam.martynchyk;

public class Main {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        Triangle triangle = new Triangle();
        triangle.initializeCoordinates();
        System.out.println(triangle.toString());
        System.out.println(triangle.area());
    }
}
