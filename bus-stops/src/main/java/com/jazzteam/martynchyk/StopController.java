package com.jazzteam.martynchyk;

import com.jazzteam.martynchyk.entity.Bus;
import com.jazzteam.martynchyk.entity.Stop;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StopController {
    private int numberOfBus;
    private int numberOfStop;
    private static final int STOP_SIZE = 3;

    private List<Stop> stops;
    private List<Bus> buses;

    public StopController(int numberOfStop, int numberOfBus) {
        this.numberOfBus = numberOfBus;
        this.numberOfStop = numberOfStop;
    }

    public void startSimulation() {
        initializeStops();
        initializeBuses();
    }

    private List<Bus> initializeStops() {
        stops = new ArrayList<>();
        for (int i = 0; i < numberOfStop; i++) {
            stops.add(new Stop(STOP_SIZE));
        }
        return buses;
    }

    private List<Bus> initializeBuses() {
        buses = new ArrayList<>();
        for (int i = 0; i < numberOfBus; i++) {
            buses.add(new Bus(this));
        }
        return buses;
    }

    public boolean isStopFree(int index) {
        return stops.get(index).isFree();
    }

    public synchronized int takeFreePlace(int index) {
        return stops.get(index).takeFreePlace();
    }

    public void freePlace(int stopIndex, int placeIndex) {
        stops.get(stopIndex).freePlace(placeIndex);
    }

    public String viewStops() {
        StringBuilder out = new StringBuilder();
        for (Stop stop : stops) {
            out.append(stop.toString());
        }
        return out.toString();
    }
}
