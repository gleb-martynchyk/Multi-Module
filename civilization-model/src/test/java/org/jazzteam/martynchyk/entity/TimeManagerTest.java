package org.jazzteam.martynchyk.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.building.providing_implementations.Farm;
import org.jazzteam.martynchyk.entity.units.Settler;
import org.jazzteam.martynchyk.entity.units.Trader;
import org.jazzteam.martynchyk.entity.units.Unit;
import org.jazzteam.martynchyk.entity.units.Worker;
import org.jazzteam.martynchyk.entity.units.military.BaseWarrior;
import org.jazzteam.martynchyk.entity.units.military.Scout;
import org.jazzteam.martynchyk.entity.units.military.Warrior;
import org.jazzteam.martynchyk.usecases.BattleWithTheCityTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

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
        civilization.addCity(city);
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
    public void testCityUnitsHealthReducedWhenFoodShortage() {
        Unit unit = new Trader();
        city.addUnit(unit);

        double expectedUnitHealth = unit.getHealthPoint();
        city.getFood().setAmount(0);
        city.doTick();
        double actualUnitHealth = unit.getHealthPoint();

        assertTrue(actualUnitHealth != expectedUnitHealth);
    }

    @Test
    public void testCityDeadUnitsRemoves() {

        city.addUnit(new Worker());
        city.addUnit(new Worker());
        city.addUnit(new Settler());

        int expectedUnitsCount = city.getUnits().size() - 1;
        city.getUnits().get(1).setHealthPoint(-50);

        city.doTick();

        assertEquals(city.getUnits().size(), expectedUnitsCount);
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
    public void testTimeManagerDoTickFoodReduceWhenNoResidents() {
        int expected = city.getFood().getAmount();
        timeManager.doTick();
        int actual = city.getFood().getAmount();
        assertEquals(actual, expected);
    }

    @Test
    public void testTimeManagerDoTickDoNotThrowsConcurrentModificationException() {
        Civilization actualCiviliation = new Civilization();
        actualCiviliation.setName("Russia");

        City moscow = new City(actualCiviliation);
        moscow.setName("Moscow");
        actualCiviliation.addCity(moscow);
        actualCiviliation.setCapital(moscow);

        City samara = new City(actualCiviliation);
        samara.setName("Samara");
        actualCiviliation.addCity(samara);

        moscow.addUnit(new Settler());
        moscow.addUnit(new Trader());

        samara.addUnit(new Trader());
        samara.addUnit(new Worker());
        samara.addUnit(new Scout());

        List<Civilization> civilizationList = new ArrayList<>();
        civilizationList.add(actualCiviliation);
        TimeManager timeManager = new TimeManager(civilizationList);
        timeManager.doTick();

        assertNotNull(actualCiviliation);
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
    public void testTimeManagerDoTickFoodReduceInManyCities() {
        City city1 = new City(civilization);
        City city2 = new City(civilization);
        civilization.addCity(city1);
        civilization.addCity(city2);

        int expected1 = city1.getFood().getAmount();
        int expected2 = city2.getFood().getAmount();

        city1.addUnit(new Worker());
        city2.addUnit(new Scout());

        timeManager.doTick();
        int actual1 = city1.getFood().getAmount();
        int actual2 = city2.getFood().getAmount();

        assertTrue(actual1 < expected1 && actual2 < expected2);
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
    public void testTimeManagerDoNegativeTicks() {
        int expected = city.getFood().getAmount();
        Farm farm = new Farm();
        city.addProducingBuildings(farm);
        timeManager.doTicks(-10);
        int actual = city.getFood().getAmount();
        assertEquals(expected, actual);
    }


}