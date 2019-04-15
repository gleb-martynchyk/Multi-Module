package com.jazzteam.martynchyk.entity;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class StopTest {
    private static final int CAPACITY = 3;
    private Stop stop = new Stop(CAPACITY);


    @Test
    public void testIsFree() {
        boolean[] places = new boolean[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            places[i] = true;
        }
        places[0] = false;
        stop.setPlaces(places);
        assertTrue(stop.isFree());
    }

    @Test
    public void testIsFreeNegative() {
        boolean[] places = new boolean[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            places[i] = true;
        }
        stop.setPlaces(places);
        assertFalse(stop.isFree());
    }

    @Test
    public void testTakeFreePlace() {
        boolean[] places = new boolean[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            places[i] = true;
        }
        int i = CAPACITY - 1;
        places[i] = false;
        stop.setPlaces(places);
        assertEquals(stop.takeFreePlace(), i);
        assertTrue(stop.getPlaces()[i]);
    }

    @Test
    public void testTakeFreePlaceNegative() {
        boolean[] places = new boolean[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            places[i] = true;
        }
        stop.setPlaces(places);
        assertEquals(stop.takeFreePlace(), -1);
    }

    @Test
    public void testFreePlace() {
        boolean[] places = new boolean[CAPACITY];
        for (int i = 0; i < CAPACITY; i++) {
            places[i] = true;
        }
        int i = CAPACITY - 1;
        stop.setPlaces(places);
        stop.freePlace(i);
        assertFalse(stop.getPlaces()[i]);
    }
}