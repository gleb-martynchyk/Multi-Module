package com.jazzteam.martynchyk.entity;

import com.jazzteam.martynchyk.StopController;

import static java.lang.Thread.sleep;

public class Bus {

    private Thread thread;
    private StopController stopController;

    public Bus(StopController controller) {
        this.stopController = controller;
        Runnable task = this::tryToTakePlace;
        thread = new Thread(task);
        thread.start();
    }

    private void tryToTakePlace() {
        int i, myPlace;
        while (true) {
            for (i = 0; i < stopController.getNumberOfStop(); i++) {
                if (stopController.isStopFree(i)) {
                    myPlace = stopController.takeFreePlace(i);
                    if (myPlace >= 0) {
                        System.out.println("Занял:"+i+" "+ myPlace);
                        try {
                            sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        stopController.freePlace(i, myPlace);
                    }
                } else {
                    try {
                        System.out.println("Нет свободных в: " + i);
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
