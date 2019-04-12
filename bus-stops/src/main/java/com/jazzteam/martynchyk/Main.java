package com.jazzteam.martynchyk;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        StopController controller = new StopController(3, 50);
        controller.startSimulation();
        System.out.println("Вызвал и ожидаю-------------------");

        Thread print = new Thread(() -> {
            while (true) {
                System.out.println(controller.viewStops());
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        print.start();
    }
}