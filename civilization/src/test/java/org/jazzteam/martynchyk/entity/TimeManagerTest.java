package org.jazzteam.martynchyk.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.building.Farm;
import org.jazzteam.martynchyk.entity.units.Worker;
import org.jazzteam.martynchyk.entity.units.military.BaseWarrior;
import org.jazzteam.martynchyk.entity.units.military.Warrior;
import org.jazzteam.martynchyk.use_cases.BattleWithTheCityTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TimeManagerTest {

    private Civilization civilization = new Civilization();
    private City city;
    private Logger log = LogManager.getLogger(BattleWithTheCityTest.class);
    private TimeManager timeManager;
    private List<Civilization> civilizationList = new ArrayList<>();

    @BeforeMethod
    public void setUp() {
        civilization = new Civilization();
        city = new City(civilization);
        civilizationList.clear();
        civilizationList.add(civilization);
        timeManager = new TimeManager(civilizationList);
    }

    @Test
    public void testCityDoTickFoodReduceWithZeroResidents() {
        int expected = city.getFood().getAmount();
        city.doTick();
        int actual = city.getFood().getAmount();
        assertEquals(actual, expected);
    }

    @Test
    public void testCityDoTickFoodReduce() {
        int expected = city.getFood().getAmount();
        city.addUnit(new Worker());
        city.doTick();
        int actual = city.getFood().getAmount();
        assertTrue(actual < expected);
    }

    @Test
    public void testCityDoTickFoodIncrease() {
        int expected = city.getFood().getAmount();
        city.addProducingBuildings(new Farm());
        city.doTick();
        int actual = city.getFood().getAmount();
        assertTrue(actual > expected);
    }

    @Test
    public void testCityDoTickRemoveDeadBesiegeUnits() {
        BaseWarrior warrior = new Warrior();
        BaseWarrior warrior1 = new Warrior();
        BaseWarrior warrior2 = new Warrior();
        warrior.laySiege(city);
        warrior1.laySiege(city);
        warrior2.laySiege(city);
        int expected = city.getBesiegeUnits().size();
        city.doTick();
        warrior1.setHealthPoint(-1);
        warrior2.setHealthPoint(-1);
        city.doTick();
        int actual = city.getBesiegeUnits().size();

        assertTrue(actual + 2 == expected);
    }

    @Test
    public void testTimeManagerDoTickFoodReduceWithZeroResidents() {
        int expected = city.getFood().getAmount();
        timeManager.doTick();
        int actual = city.getFood().getAmount();
        assertEquals(actual, expected);
    }

    @Test
    public void testTimeManagerDoTickFoodReduce() {
        int expected = city.getFood().getAmount();
        city.addUnit(new Worker());
        timeManager.doTick();
        int actual = city.getFood().getAmount();
        assertTrue(actual < expected);
    }

    @Test
    public void testTimeManagerDoTickFoodIncrease() {
        int expected = city.getFood().getAmount();
        city.addProducingBuildings(new Farm());
        timeManager.doTick();
        int actual = city.getFood().getAmount();
        assertTrue(actual > expected);
    }

    @Test
    public void testTimeManagerDoTicksFoodIncrease() {
        int expected = city.getFood().getAmount();
        Farm farm = new Farm();
        city.addProducingBuildings(farm);
        timeManager.doTicks(10);
        int actual = city.getFood().getAmount();
        assertEquals(expected + 10 * Farm.productionQuantity, actual);
    }

    @Test
    public void testTimeManagerDoZeroTicks() {
        int expected = city.getFood().getAmount();
        Farm farm = new Farm();
        city.addProducingBuildings(farm);
        timeManager.doTicks(0);
        int actual = city.getFood().getAmount();
        assertEquals(expected, actual);
    }

    @Test
    public void testTimeManagerDoNegativeZeroTicks() {
        int expected = city.getFood().getAmount();
        Farm farm = new Farm();
        city.addProducingBuildings(farm);
        timeManager.doTicks(-10);
        int actual = city.getFood().getAmount();
        assertEquals(expected, actual);
    }


}