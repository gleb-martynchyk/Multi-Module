package com.jazzteam.martynchyk;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StopControllerTest {

    private StopController controller;

    @BeforeMethod
    public void setUp() {
        controller = new StopController(3, 7);
    }

    @Test
    public void testStartSimulation() {
        controller.startSimulation();
        assertNotEquals(controller.getBuses().size(),0);
        assertNotEquals(controller.getStops().size(),0);
    }

    @Test
    public void testStartSimulationNegative() {
        controller.setNumberOfBus(0);
        controller.setNumberOfStop(0);
        controller.startSimulation();
        assertEquals(controller.getBuses().size(),0);
        assertEquals(controller.getStops().size(),0);
    }

    @Test
    public void testIsStopFree() {
        boolean [] places=new boolean[]{false,false,true};
        controller.startSimulation();
        controller.getStops().get(0).setPlaces(places);
        assertTrue(controller.isStopFree(0));
    }

    @Test
    public void testIsStopFreeNegative() {
        boolean [] places=new boolean[]{true,true,true};
        controller.setNumberOfBus(0);
        controller.startSimulation();
        controller.getStops().get(0).setPlaces(places);
        assertFalse(controller.isStopFree(0));
    }

    @Test
    public void testTakeFreePlace() {
        controller.startSimulation();
        int currentPlace=controller.takeFreePlace(1);
        assertTrue(controller.getStops().get(1).getPlaces()[currentPlace]);
    }

    @Test
    public void testFreePlace() {
        controller.startSimulation();
        int currentPlace=controller.takeFreePlace(1);
        controller.freePlace(1,currentPlace);
        assertFalse(controller.getStops().get(1).getPlaces()[currentPlace]);
    }
}