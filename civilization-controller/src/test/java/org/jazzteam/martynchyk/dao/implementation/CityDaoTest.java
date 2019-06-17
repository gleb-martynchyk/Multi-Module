package org.jazzteam.martynchyk.dao.implementation;

import org.jazzteam.martynchyk.config.CityDaoConfig;
import org.jazzteam.martynchyk.config.HibernateXMLConfig;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.building.improving_implementations.Barrack;
import org.jazzteam.martynchyk.entity.building.improving_implementations.DefensiveWall;
import org.jazzteam.martynchyk.entity.building.providing_implementations.*;
import org.jazzteam.martynchyk.entity.resources.implementation.Gold;
import org.jazzteam.martynchyk.entity.trade.TradeRoute;
import org.jazzteam.martynchyk.entity.units.Trader;
import org.jazzteam.martynchyk.entity.units.Worker;
import org.jazzteam.martynchyk.entity.units.military.HorseMan;
import org.jazzteam.martynchyk.entity.units.military.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@WebAppConfiguration
@ContextConfiguration(classes = {HibernateXMLConfig.class, CityDaoConfig.class})
public class CityDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private CityDao cityDao;
    @Autowired
    private CivilizationDao civilizationDao;

    private Civilization civilization;

    @BeforeMethod
    public void setUp() {
        civilization = new Civilization();
        civilizationDao.create(civilization);
    }

    @Test
    public void testCreateAndFindCity() {
        City city = new City(civilization);
        cityDao.create(city);
        city.addUnit(new Trader());
        city.addUnit(new Worker());
        city.addUnit(new Scout());
        city.addUnit(new HorseMan());

        city.getResources().put(Gold.class, new Gold(111));

        assertEquals(cityDao.find(city.getId()), city);
    }

    @Test
//    @Rollback(false)
    public void testCreateAndFindCityWithBuildings() {
        City city = new City(civilization);
        cityDao.create(city);

        city.addUnit(new Trader());
        city.addUnit(new Worker());
        city.addUnit(new Scout());
        city.addUnit(new HorseMan());

        city.addImprovingBuildings(new Barrack());
        city.addImprovingBuildings(new Barrack());
        city.addImprovingBuildings(new DefensiveWall());

        city.addProducingBuildings(new Farm());
        city.addProducingBuildings(new Temple());
        city.addProducingBuildings(new Market());
        city.addProducingBuildings(new Mine());
        city.addProducingBuildings(new Campus());

        city.getResources().put(Gold.class, new Gold(111));
        City actualCity = cityDao.find(city.getId());

        assertEquals(actualCity, city);
    }

    @Test
//    @Rollback(false)
    public void testCreateAndFindCityWithTradeRoutes() {
        Civilization civilizationA = new Civilization();
        Civilization civilizationB = new Civilization();

        City cityA = new City(civilizationA);
        City cityB = new City(civilizationB);
        cityA.setName("cityA");
        cityB.setName("cityB");

        civilizationA.addCity(cityA);
        civilizationB.addCity(cityB);

        TradeRoute tradeRoute = TradeRoute.createTradeRoute(cityA, cityB);

        civilizationDao.create(civilizationB);
        civilizationDao.create(civilizationA);
        cityDao.create(cityA);
        cityDao.create(cityB);

        City actualCity = cityDao.find(cityA.getId());

        assertEquals(actualCity, cityA);
        assertTrue(actualCity.getTradeRoutes().contains(tradeRoute));
    }

    @Test
    public void testFindCityDoesntExist() {
        City city = new City(civilization);
        assertNull(cityDao.find(city.getId()));
    }

    @Test
    public void testFindCityWithNegativeId() {
        assertNull(cityDao.find(-1));
    }

    @Test
    public void testCreateCity() {
        City city = new City(civilization);
        cityDao.create(city);
        assertEquals(cityDao.find(city.getId()), city);
    }


    @Test
    public void testFindAllCities() {
        City cityA = new City(civilization);
        City cityB = new City(civilization);
        City cityC = new City(civilization);

        cityDao.create(cityA);
        cityDao.create(cityB);
        cityDao.create(cityC);

        List<City> citiesExpected = new ArrayList<>();
        citiesExpected.add(cityA);
        citiesExpected.add(cityB);
        citiesExpected.add(cityC);

        assertEquals(cityDao.findAll(), citiesExpected);
    }

    @Test
    public void testFindAllCitysIfEmpty() {
        assertTrue(cityDao.findAll().isEmpty());
    }
}