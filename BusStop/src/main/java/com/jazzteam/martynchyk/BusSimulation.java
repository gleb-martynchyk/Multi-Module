package com.jazzteam.martynchyk;

import com.jazzteam.martynchyk.entity.Bus;
import com.jazzteam.martynchyk.entity.Stop;

import java.util.LinkedList;
import java.util.List;

public class BusSimulation {

    private int numberOfBus;
    private int numberOfStop;

    private List<Stop> stops = new LinkedList<>();
    private List<Bus> buses = new LinkedList<>();

    public BusSimulation(int numberOfBus, int numberOfStop) {
        this.numberOfBus = numberOfBus;
        this.numberOfStop = numberOfStop;
    }

    public void startSimulation() {

    }

    public List<Bus> createStops() {
        return null;
    }

    public List<Bus> createBuses() {
        return null;
    }
}
