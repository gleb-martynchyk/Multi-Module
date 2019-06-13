package org.jazzteam.martynchyk.dao.implementation;

import org.jazzteam.martynchyk.config.CityDaoConfig;
import org.jazzteam.martynchyk.config.HibernateXMLConfig;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.resources.implementation.Gold;
import org.jazzteam.martynchyk.entity.units.Trader;
import org.jazzteam.martynchyk.entity.units.Worker;
import org.jazzteam.martynchyk.entity.units.military.HorseMan;
import org.jazzteam.martynchyk.entity.units.military.Scout;
import org.springframework.beans.factory.annotation.Autowired;
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