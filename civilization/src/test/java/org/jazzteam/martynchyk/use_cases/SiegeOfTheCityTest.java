package org.jazzteam.martynchyk.use_cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.units.military.BaseWarrior;
import org.jazzteam.martynchyk.entity.units.military.Warrior;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class SiegeOfTheCityTest {

    private Civilization civilization = new Civilization();
    private Logger log = LogManager.getLogger(BattleWithTheCityTest.class);

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void isCitySiegedByOneWarrior() {
        City city = new City(civilization);
        BaseWarrior warrior = new Warrior();
        warrior.laySiege(city);

        assertFalse(city.isSieged());
    }

    @Test
    public void isCitySiegedByManyWarrior() {
        City city = new City(civilization);
        BaseWarrior warrior;
        for (int i = 0; i < 20; i++) {
            warrior = new Warrior();
            warrior.laySiege(city);
        }
        assertTrue(city.isSieged());
    }

    @Test
    public void isCitySiegeBlockResourceSupport() {
        City city = new City(civilization);
        city.setSieged(true);
        assertFalse(city.requestSupport());
    }

    @Test
    public void isNotSiegedCityCanResourceSupport() {
        City city = new City(civilization);
        city.setSieged(false);
        assertTrue(city.requestSupport());
    }

}
