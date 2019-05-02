package org.jazzteam.martynchyk.entity;

import org.jazzteam.martynchyk.entity.units.military.Archer;
import org.jazzteam.martynchyk.entity.units.military.BaseWarrior;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CombatTest {

    BaseWarrior archerA = new Archer();
    BaseWarrior archerB = new Archer();

    @BeforeMethod
    public void setUp() {
        archerA.setHealthPoint(100);
        archerB.setHealthPoint(100);
        archerA.setStrength(1);
        archerB.setStrength(10);
    }

    @Test
    public void testCalculateDamageStrengthDifference() {
        archerA.setStrength(2);
        archerB.setStrength(10);
        assertTrue(archerB.calculateDamage(archerA) > archerA.calculateDamage(archerB));
    }

    @Test
    public void testCalculateDamage() {
        archerA.setStrength(2);
        archerB.setStrength(10);
        int expectedDamage = 21;
        assertEquals(archerA.calculateDamage(archerB), expectedDamage, expectedDamage * 0.25);
    }
}