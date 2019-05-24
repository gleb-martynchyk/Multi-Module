package org.jazzteam.martynchyk.entity.building;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.use_cases.SiegeOfTheCityTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CityBuilding {

    private Logger log = LogManager.getLogger(SiegeOfTheCityTest.class);
    private Civilization civilization = new Civilization();
    private City city;

    @BeforeMethod
    public void setUp() {
        city = new City(civilization);
    }

    @Test
    public void isBuildingsAreAddedTemple() {
        city.addBuilding(new Temple());
        assertTrue(!city.getBuildings().isEmpty());
    }

    @Test
    public void isBuildingsAreAddedBarrack() {
        city.addBuilding(new Barrack());
        assertTrue(!city.getBuildings().isEmpty());
    }

    @Test
    public void isBuildingsAreAddedDefensiveWall() {
        city.addBuilding(new DefensiveWall());
        assertTrue(!city.getBuildings().isEmpty());
    }

    @Test
    public void isBuildingsAreAddedFarm() {
        city.addBuilding(new Farm());
        assertTrue(!city.getBuildings().isEmpty());
    }

    @Test
    public void areBuildingsRemovedOneElement() {
        Building farm = new Farm();
        city.addBuilding(farm);
        city.removeBuilding(farm);
        assertTrue(city.getBuildings().isEmpty());
    }

    @Test
    public void areBuildingsRemovedManyElements() {
        Building farm = new Farm();
        Building mine = new Mine();
        city.addBuilding(farm);
        city.addBuilding(mine);
        city.removeBuilding(farm);
        city.removeBuilding(mine);
        assertTrue(city.getBuildings().isEmpty());
    }
}