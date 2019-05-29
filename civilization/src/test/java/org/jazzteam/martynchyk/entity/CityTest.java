package org.jazzteam.martynchyk.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.resources.implementation.Food;
import org.jazzteam.martynchyk.entity.resources.implementation.Production;
import org.jazzteam.martynchyk.entity.units.Worker;
import org.jazzteam.martynchyk.use_cases.BattleWithTheCityTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class CityTest {

    private Civilization civilization;
    private City city;
    private Logger log = LogManager.getLogger(BattleWithTheCityTest.class);

    @BeforeMethod
    public void setUp() {
        civilization = new Civilization();
        city = new City(civilization);
    }

    @Test
    public void testResourcesAreEmptyInNewCity() {
        assertFalse(city.resourcesAreEmpty());
    }

    @Test
    public void testResourcesAreEmptyFood() {
        city.setFood(new Food(0));
        assertTrue(city.resourcesAreEmpty());
    }

    @Test
    public void testResourcesAreEmptyProduction() {
        city.setProduction(new Production(0));
        assertTrue(city.resourcesAreEmpty());
    }

    @Test
    public void testRequestResourcesIsImproveFood() {
        City city1 = new City(civilization);
        city1.getResources().get(Food.class.getName()).setAmount(20);
        city.getResources().get(Food.class.getName()).setAmount(0);
        int expected = city.getResources().get(Food.class.getName()).getAmount();
        city.requestResourcesFromCivilization();
        int actual = city.getResources().get(Food.class.getName()).getAmount();
        assertNotEquals(expected, actual);
    }

    @Test
    public void testRequestResourcesIsImproveFoodWithUnit() {
        City city1 = new City(civilization);
        city1.getResources().get(Food.class.getName()).setAmount(20);
        city.getResources().get(Food.class.getName()).setAmount(0);
        city.addUnit(new Worker());
        city.addUnit(new Worker());
        int expected = city.getResources().get(Food.class.getName()).getAmount();
        city.requestResourcesFromCivilization();
        int actual = city.getResources().get(Food.class.getName()).getAmount();
        assertNotEquals(expected, actual);
    }

    @Test
    public void testRequestToManyCities() {
        City city1 = new City(civilization);
        City city2 = new City(civilization);

        city1.getResources().get(Food.class.getName()).setAmount(10);
        city2.getResources().get(Food.class.getName()).setAmount(10);
        city.getResources().get(Food.class.getName()).setAmount(0);

        int expected = city.getResources().get(Food.class.getName()).getAmount();
        city.requestResourcesFromCivilization();
        int actual = city.getResources().get(Food.class.getName()).getAmount();
        assertNotEquals(expected, actual);
    }

    @Test
    public void testRequestToManyCitiesAndNotRemainEmpty() {
        City city1 = new City(civilization);
        City city2 = new City(civilization);

        city1.getResources().get(Food.class.getName()).setAmount(10);
        city2.getResources().get(Food.class.getName()).setAmount(10);
        city.getResources().get(Food.class.getName()).setAmount(0);

        city.requestResourcesFromCivilization();
        log.info(city.getResources().get(Food.class.getName()).getAmount());
        log.info(city1.getResources().get(Food.class.getName()).getAmount());
        log.info(city2.getResources().get(Food.class.getName()).getAmount());

        assertFalse(city.resourcesAreEmpty() && city1.resourcesAreEmpty() && city2.resourcesAreEmpty());
    }

    @Test
    public void testRequestToManyCitiesWithNoFood() {
        City city1 = new City(civilization);
        City city2 = new City(civilization);

        city1.getResources().get(Food.class.getName()).setAmount(7);
        city2.getResources().get(Food.class.getName()).setAmount(7);
        city.getResources().get(Food.class.getName()).setAmount(0);

        city.getResources().get(Food.class.getName()).getExcess(city);
        boolean result = city.requestResourcesFromCivilization();

        log.info(city.getResources().get(Food.class.getName()).getAmount());
        log.info(city1.getResources().get(Food.class.getName()).getAmount());
        log.info(city2.getResources().get(Food.class.getName()).getAmount());

        assertFalse(result);
    }

    @Test
    public void testRequestFromCityWIthMaxResource() {
        City city1 = new City(civilization);
        City city2 = new City(civilization);

        city1.getResources().get(Food.class.getName()).setAmount(100);
        city2.getResources().get(Food.class.getName()).setAmount(10);
        city.getResources().get(Food.class.getName()).setAmount(0);

        city.getResources().get(Food.class.getName()).getExcess(city);
        boolean result = city.requestResourcesFromCivilization();

        log.info(city.getResources().get(Food.class.getName()).getAmount());
        log.info(city1.getResources().get(Food.class.getName()).getAmount());
        log.info(city2.getResources().get(Food.class.getName()).getAmount());

        assertTrue(result);
    }
}