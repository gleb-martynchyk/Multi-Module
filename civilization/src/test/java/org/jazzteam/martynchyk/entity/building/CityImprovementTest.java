package org.jazzteam.martynchyk.entity.building;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.use_cases.SiegeOfTheCityTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CityImprovementTest {

    private Logger log = LogManager.getLogger(SiegeOfTheCityTest.class);
    private Civilization civilization = new Civilization();
    private City city;

    @BeforeMethod
    public void setUp() {
        city = new City(civilization);
    }

    @Test
    public void isBarrackImproveStrength() {
        int strengthExpected = city.getStrength();
        city.addBuilding(new Barrack());
        int strengthActual = city.getStrength();
        assertTrue(strengthActual > strengthExpected);
    }

    @Test
    public void isDefenseWallImproveStrength() {
        double defenceExpected = city.getDefence();
        city.addBuilding(new DefensiveWall());
        double defenceActual = city.getDefence();
        assertTrue(defenceActual > defenceExpected);
    }

    @Test
    public void isStrengthDecreaseWhenBarrackRemoved() {
        int strengthExpected = city.getStrength();
        Building barrack = new Barrack();
        city.addBuilding(barrack);
        city.removeBuilding(barrack);
        int strengthActual = city.getStrength();
        assertEquals(strengthActual, strengthExpected);
    }

    @Test
    public void isDefenseDecreaseWhenWallRemoved() {
        double defenceExpected = city.getDefence();
        Building wall = new DefensiveWall();
        city.addBuilding(wall);
        city.removeBuilding(wall);
        double defenceActual = city.getDefence();
        assertEquals(defenceActual, defenceExpected);
    }
}