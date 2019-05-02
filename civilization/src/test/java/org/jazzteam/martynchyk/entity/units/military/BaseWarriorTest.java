package org.jazzteam.martynchyk.entity.units.military;

import org.jazzteam.martynchyk.entity.City;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BaseWarriorTest {

    BaseWarrior archerA = new Archer();
    BaseWarrior archerB = new Archer();

    @BeforeMethod
    public void setUp() {
        archerA.setHealthPoint(100);
        archerB.setHealthPoint(100);
        archerA.setStrength(2);
        archerB.setStrength(10);
    }

    @Test
    public void attackTestSimple() {
        int expectedHealthA = archerA.getHealthPoint();
        int expectedHealthB = archerB.getHealthPoint();
        archerA.attack(archerB);
        assertTrue(archerA.getHealthPoint() != expectedHealthA
                && archerB.getHealthPoint() != expectedHealthB);
    }

    @Test
    public void battleUnitVsCity() {
        City city = new City();
        city.setHealthPoint(100);
        int expectedCityHealth = city.getHealthPoint();
        int expectedUnitHealth = archerA.getHealthPoint();

        archerA.attack(city);
        assertTrue(city.getHealthPoint() != expectedCityHealth
                && archerA.getHealthPoint() != expectedUnitHealth);
    }

    @Test(dataProvider = "ValidStrengthAndDamage")
    public void attackTest(double[] input) {
        archerA.setStrength((int) input[0]);
        archerB.setStrength((int) input[1]);

        archerA.attack(archerB);
        assertEquals((double) archerB.getHealthPoint(), input[2], input[2] * 0.25);
    }

    @Test
    public void attackWithStrengthDifference() {
        archerA.attack(archerB);
        assertTrue(archerB.getHealthPoint() > archerA.getHealthPoint());
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