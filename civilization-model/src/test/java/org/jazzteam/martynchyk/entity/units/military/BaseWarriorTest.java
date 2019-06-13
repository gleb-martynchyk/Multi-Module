package org.jazzteam.martynchyk.entity.units.military;

import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BaseWarriorTest {

    private Civilization civilization = new Civilization();
    BaseWarrior warrior1 = new Warrior();
    BaseWarrior warrior2 = new Warrior();

    @BeforeMethod
    public void setUp() {
        warrior1.setHealthPoint(100);
        warrior2.setHealthPoint(100);
        warrior1.setStrength(2);
        warrior2.setStrength(10);
    }

    @Test
    public void fightTestSimple() {
        double expectedHealthA = warrior1.getHealthPoint();
        double expectedHealthB = warrior2.getHealthPoint();
        warrior1.fight(warrior2);
        assertTrue(warrior1.getHealthPoint() != expectedHealthA
                && warrior2.getHealthPoint() != expectedHealthB);
    }

    @Test
    public void battleUnitVsCity() {
        City city = new City(civilization);
        city.setHealthPoint(100);
        double expectedCityHealth = city.getHealthPoint();
        double expectedUnitHealth = warrior1.getHealthPoint();

        warrior1.fight(city);
        assertTrue(city.getHealthPoint() != expectedCityHealth
                && warrior1.getHealthPoint() != expectedUnitHealth);
    }

    @Test(dataProvider = "ValidStrengthAndDamage")
    public void fightTest(double[] input) {
        warrior1.setStrength((int) input[0]);
        warrior2.setStrength((int) input[1]);

        warrior1.fight(warrior2);
        assertEquals(warrior2.getHealthPoint(), input[2], input[2] * 0.25);
    }

    @Test
    public void fightWithStrengthDifference() {
        warrior1.fight(warrior2);
        assertTrue(warrior2.getHealthPoint() > warrior1.getHealthPoint());
    }

    @Test
    public void fightHorseManVsSpearmanIsDamageIncrease() {
        BaseWarrior horseMan = new HorseMan();
        BaseWarrior spearman = new Spearman();
        BaseWarrior warrior = new Warrior();

        horseMan.setStrength(10);
        warrior.setStrength(10);

        spearman.fight(horseMan);
        spearman.fight(warrior);

        assertTrue(warrior.getHealthPoint() > horseMan.getHealthPoint());
    }

    @DataProvider(name = "ValidStrengthAndDamage")
    public Object[][] getStrengthAndDamage() {
        return new Object[][]{
                {2, 10, 79},
                {10, 2, 59},
                {15, 66, 97},
                {22, 8, 46},
                {22, 88, 98}
        };
    }
}