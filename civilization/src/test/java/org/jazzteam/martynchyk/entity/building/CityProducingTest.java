package org.jazzteam.martynchyk.entity.building;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.use_cases.SiegeOfTheCityTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CityProducingTest {

    private Logger log = LogManager.getLogger(SiegeOfTheCityTest.class);
    private Civilization civilization = new Civilization();
    private City city;

    @BeforeMethod
    public void setUp() {
        city = new City(civilization);
    }

    @Test
    public void isFarmProduceFood() {
        int foodExpected = city.getFood();
        city.addBuilding(new Farm());
        city.collectResources();
        int foodActual = city.getFood();
        assertTrue(foodActual > foodExpected);
    }

    @Test
    public void isFarmProduceFoodRepeatedly() {
        city.addBuilding(new Farm());
        city.collectResources();
        int foodExpected = city.getFood();
        city.collectResources();
        int foodActual = city.getFood();
        assertTrue(foodActual > foodExpected);
    }

    @Test
    public void isMineProduceProduction() {
        int productionExpected = city.getProduction();
        city.addBuilding(new Mine());
        city.collectResources();
        int productionActual = city.getProduction();
        assertTrue(productionActual > productionExpected);
    }

    @Test
    public void isMineProduceProductionRepeatedly() {
        city.addBuilding(new Mine());
        city.collectResources();
        int productionExpected = city.getProduction();
        city.collectResources();
        int productionActual = city.getProduction();
        log.info(productionActual + " " + productionExpected);
        assertTrue(productionActual > productionExpected);
    }

    @Test
    public void isCampusProduceScience() {
        int productionExpected = city.getCivilization().getScience();
        city.addBuilding(new Campus());
        city.collectResources();
        int productionActual = city.getCivilization().getScience();
        assertTrue(productionActual > productionExpected);
    }

    @Test
    public void isCampusProduceScienceRepeatedly() {
        city.addBuilding(new Campus());
        city.collectResources();
        int scienceExpected = city.getCivilization().getScience();
        city.collectResources();
        int scienceActual = city.getCivilization().getScience();
        assertTrue(scienceActual > scienceExpected);
    }

    @Test
    public void isMarketProduceGold() {
        int goldExpected = city.getCivilization().getGold();
        city.addBuilding(new Market());
        city.collectResources();
        int goldActual = city.getCivilization().getGold();
        assertTrue(goldActual > goldExpected);
    }

    @Test
    public void isMarketProduceGoldRepeatedly() {
        city.addBuilding(new Market());
        city.collectResources();
        int goldExpected = city.getCivilization().getGold();
        city.collectResources();
        int goldActual = city.getCivilization().getGold();
        assertTrue(goldActual > goldExpected);
    }

    @Test
    public void isTempleProduceFaith() {
        int faithExpected = city.getCivilization().getFaith();
        city.addBuilding(new Temple());
        city.collectResources();
        int faithActual = city.getCivilization().getFaith();
        assertTrue(faithActual > faithExpected);
    }

    @Test
    public void isTempleProduceFaithRepeatedly() {
        city.addBuilding(new Temple());
        city.collectResources();
        int faithExpected = city.getCivilization().getFaith();
        city.collectResources();
        int faithActual = city.getCivilization().getFaith();
        assertTrue(faithActual > faithExpected);
    }
}