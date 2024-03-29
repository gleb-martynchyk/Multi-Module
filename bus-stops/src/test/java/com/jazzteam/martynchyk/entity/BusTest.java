package com.jazzteam.martynchyk.entity;

import com.jazzteam.martynchyk.StopController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class BusTest {
    private Logger log = LogManager.getLogger(BusTest.class);

    @Test
    private void main() throws InterruptedException {

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
        //print.join();
        Thread.currentThread().sleep(10000);
    }
}