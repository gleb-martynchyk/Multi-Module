package com.jazzteam.martynchyk.entity;

import com.jazzteam.martynchyk.StopController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.Thread.sleep;

public class Bus {

    private Thread thread;
    private StopController stopController;
    private boolean running = false;
    private static Logger log = LogManager.getLogger(Bus.class);

    public Bus(StopController controller) {
        this.stopController = controller;
        Runnable task = this::tryToTakePlace;
        thread = new Thread(task);
        thread.start();
    }

    private void tryToTakePlace() {
        int i, myPlace;
        running = true;
        while (running) {
            for (i = 0; i < stopController.getNumberOfStop(); i++) {
                if (stopController.isStopFree(i)) {
                    myPlace = stopController.takeFreePlace(i);
                    if (myPlace >= 0) {
                        log.info("Занял:" + i + " " + myPlace);
                        try {
                            sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                        stopController.freePlace(i, myPlace);
                    }
                } else {
                    try {
                        log.info("Нет свободных в: " + i);
                        sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public void stop() {
        running = false;
    }
}
