package org.jazzteam.martynchyk.entity;

import org.jazzteam.martynchyk.entity.building.improving_implementations.DefensiveWall;
import org.jazzteam.martynchyk.entity.units.military.Archer;
import org.jazzteam.martynchyk.entity.units.military.BaseWarrior;
import org.jazzteam.martynchyk.entity.units.military.Warrior;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CombatTest {

    private BaseWarrior warrior1 = new Warrior();
    private BaseWarrior warrior2 = new Warrior();
    private Civilization civilization = new Civilization();

    @BeforeMethod
    public void setUp() {
        warrior1.setHealthPoint(100);
        warrior2.setHealthPoint(100);
        warrior1.setStrength(1);
        warrior2.setStrength(10);
    }

    @Test
    public void testCalculateDamageStrengthDifference() {
        warrior1.setStrength(2);
        warrior2.setStrength(10);
        assertTrue(warrior2.calculateDamage(warrior1) > warrior1.calculateDamage(warrior2));
    }

    @Test
    public void testCalculateDamageArcherUseRangedDamage() {
        BaseWarrior archer = new Archer();
        BaseWarrior warrior = new Warrior();
        warrior.setStrength(30);
        archer.setRangedStrength(30);
        archer.setStrength(0);

        int expectedDamage = 30;

        assertEquals(archer.calculateDamage(warrior), expectedDamage, expectedDamage * 0.25);
    }

    @Test
    public void testCalculateDamage() {
        warrior1.setStrength(2);
        warrior2.setStrength(10);
        int expectedDamage = 21;

        assertEquals(warrior1.calculateDamage(warrior2), expectedDamage, expectedDamage * 0.25);
    }

    @Test
    public void isDefenseWallReduceDamage() {
        City city = new City(civilization);
        City citySec = new City(civilization);
        citySec.addImprovingBuildings(new DefensiveWall());
        BaseWarrior archer = new Archer();
        archer.attack(city);
        archer.attack(citySec);

        double expected = city.getHealthPoint();
        double actual = citySec.getHealthPoint();

        assertTrue(actual > expected);
    }
}