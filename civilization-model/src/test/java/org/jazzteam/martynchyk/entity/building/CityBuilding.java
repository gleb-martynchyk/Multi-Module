package org.jazzteam.martynchyk.entity.building;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.usecases.BattleWithTheCityTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CityBuilding {

    private Logger log = LogManager.getLogger(BattleWithTheCityTest.class);
    private Civilization civilization = new Civilization();
    private City city;

    @BeforeMethod
    public void setUp() {
        city = new City(civilization);
    }

    @Test
    public void isBuildingsAreAddedTemple() {
        city.addProducingBuildings(new Temple());
        assertTrue(!city.getProducingBuildings().isEmpty());
    }

    @Test
    public void isBuildingsAreAddedBarrack() {
        city.addImprovingBuildings(new Barrack());
        assertTrue(!city.getImprovingBuildings().isEmpty());
    }

    @Test
    public void isBuildingsAreAddedDefensiveWall() {
        city.addImprovingBuildings(new DefensiveWall());
        assertTrue(!city.getImprovingBuildings().isEmpty());
    }

    @Test
    public void isBuildingsAreAddedFarm() {
        city.addProducingBuildings(new Farm());
        assertTrue(!city.getProducingBuildings().isEmpty());
    }

    @Test
    public void areBuildingsRemovedOneElement() {
        Producing farm = new Farm();
        city.addProducingBuildings(farm);
        city.removeProducingBuildings(farm);
        assertTrue(city.getProducingBuildings().isEmpty());
    }

    @Test
    public void areBuildingsRemovedManyElements() {
        Producing farm = new Farm();
        Producing mine = new Mine();
        city.addProducingBuildings(farm);
        city.addProducingBuildings(mine);
        city.removeProducingBuildings(farm);
        city.removeProducingBuildings(mine);
        assertTrue(city.getProducingBuildings().isEmpty());
    }
}