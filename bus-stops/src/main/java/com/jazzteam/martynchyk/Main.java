package com.jazzteam.martynchyk;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        Logger log = LogManager.getLogger(Main.class);

        StopController controller = new StopController(3, 7);
        controller.startSimulation();
        log.info("Вызвал и ожидаю-------------------");

        Thread print = new Thread(() -> {
            while (true) {
                log.info(controller.viewStops());
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        print.start();
    }
}