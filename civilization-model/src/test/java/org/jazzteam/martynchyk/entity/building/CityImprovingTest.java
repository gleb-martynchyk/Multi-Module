package org.jazzteam.martynchyk.entity.building;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.building.improving_implementations.Barrack;
import org.jazzteam.martynchyk.entity.building.improving_implementations.DefensiveWall;
import org.jazzteam.martynchyk.usecases.BattleWithTheCityTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CityImprovingTest {

    private Logger log = LogManager.getLogger(BattleWithTheCityTest.class);
    private Civilization civilization = new Civilization();
    private City city;

    @BeforeMethod
    public void setUp() {
        city = new City(civilization);
    }

    @Test
    public void isBarrackImproveStrength() {
        int strengthExpected = city.getStrength();
        city.addImprovingBuildings(new Barrack());
        int strengthActual = city.getStrength();
        assertTrue(strengthActual > strengthExpected);
    }

    @Test
    public void isDefenseWallImproveStrength() {
        double defenceExpected = city.getDefence();
        city.addImprovingBuildings(new DefensiveWall());
        double defenceActual = city.getDefence();
        assertTrue(defenceActual > defenceExpected);
    }

    @Test
    public void isStrengthDecreaseWhenBarrackRemoved() {
        int strengthExpected = city.getStrength();
        ImprovingEntity barrack = new Barrack();
        city.addImprovingBuildings(barrack);
        city.removeImprovingBuildings(barrack);
        int strengthActual = city.getStrength();
        assertEquals(strengthActual, strengthExpected);
    }

    @Test
    public void isDefenseDecreaseWhenWallRemoved() {
        double defenceExpected = city.getDefence();
        ImprovingEntity wall = new DefensiveWall();
        city.addImprovingBuildings(wall);
        city.removeImprovingBuildings(wall);
        double defenceActual = city.getDefence();
        assertEquals(defenceActual, defenceExpected);
    }
}