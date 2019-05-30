package org.jazzteam.martynchyk.use_cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.resources.implementation.Food;
import org.jazzteam.martynchyk.entity.units.Worker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class CityRequestResources {

    private Civilization civilization;
    private City city;
    private Logger log = LogManager.getLogger(BattleWithTheCityTest.class);

    @BeforeMethod
    public void setUp() {
        civilization = new Civilization();
        city = new City(civilization);
    }

    @Test
    public void testRequestResourcesIsImproveFood() {
        City city1 = new City(civilization);
        city1.getResources().get(Food.class).setAmount(20);
        city.getResources().get(Food.class).setAmount(0);
        int expected = city.getResources().get(Food.class).getAmount();
        city.requestResourcesFromCivilization();
        int actual = city.getResources().get(Food.class).getAmount();
        assertNotEquals(expected, actual);
    }

    @Test
    public void testRequestResourcesIsImproveFoodWithUnit() {
        City city1 = new City(civilization);
        city1.getResources().get(Food.class).setAmount(20);
        city.getResources().get(Food.class).setAmount(0);
        city.addUnit(new Worker());
        city.addUnit(new Worker());
        int expected = city.getResources().get(Food.class).getAmount();
        city.requestResourcesFromCivilization();
        int actual = city.getResources().get(Food.class).getAmount();
        assertNotEquals(expected, actual);
    }

    @Test
    public void testRequestToManyCities() {
        City city1 = new City(civilization);
        City city2 = new City(civilization);

        city1.getResources().get(Food.class).setAmount(10);
        city2.getResources().get(Food.class).setAmount(10);
        city.getResources().get(Food.class).setAmount(0);

        int expected = city.getResources().get(Food.class).getAmount();
        city.requestResourcesFromCivilization();
        int actual = city.getResources().get(Food.class).getAmount();
        assertNotEquals(expected, actual);
    }

    @Test
    public void testRequestToManyCitiesAndNotRemainEmpty() {
        City city1 = new City(civilization);
        City city2 = new City(civilization);

        city1.getResources().get(Food.class).setAmount(10);
        city2.getResources().get(Food.class).setAmount(10);
        city.getResources().get(Food.class).setAmount(0);

        city.requestResourcesFromCivilization();
        log.info(city.getResources().get(Food.class).getAmount());
        log.info(city1.getResources().get(Food.class).getAmount());
        log.info(city2.getResources().get(Food.class).getAmount());

        assertFalse(city.resourcesAreEmpty() && city1.resourcesAreEmpty() && city2.resourcesAreEmpty());
    }

    @Test
    public void testRequestToManyCitiesWithNoFood() {
        City city1 = new City(civilization);
        City city2 = new City(civilization);

        city1.getResources().get(Food.class).setAmount(7);
        city2.getResources().get(Food.class).setAmount(7);
        city.getResources().get(Food.class).setAmount(0);

        city.getResources().get(Food.class).getExcess(city);
        boolean result = city.requestResourcesFromCivilization();

        log.info(city.getResources().get(Food.class).getAmount());
        log.info(city1.getResources().get(Food.class).getAmount());
        log.info(city2.getResources().get(Food.class).getAmount());

        assertFalse(result);
    }

    @Test
    public void testRequestFromCityWIthMaxResource() {
        City city1 = new City(civilization);
        City city2 = new City(civilization);

        city1.getResources().get(Food.class).setAmount(100);
        city2.getResources().get(Food.class).setAmount(10);
        city.getResources().get(Food.class).setAmount(0);

        city.getResources().get(Food.class).getExcess(city);
        boolean result = city.requestResourcesFromCivilization();

        log.info(city.getResources().get(Food.class).getAmount());
        log.info(city1.getResources().get(Food.class).getAmount());
        log.info(city2.getResources().get(Food.class).getAmount());

        assertTrue(result);
    }
}
