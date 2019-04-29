package com.jazzteam.martynchyk.factory.creator;

import com.jazzteam.martynchyk.factory.units.Archer;
import com.jazzteam.martynchyk.factory.units.Cavalryman;
import com.jazzteam.martynchyk.factory.units.Soldier;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class UnitsCreatorTest {

    private UnitsCreator unitsCreator;

    @Test
    public void testCreateArcherUnit() {
        unitsCreator = new ArcherCreator();
        assertEquals(unitsCreator.createUnit().getClass(), Archer.class);
    }

    @Test
    public void testCreateArcherUnitNEg() {
        unitsCreator = new ArcherCreator();
        assertNotEquals(unitsCreator.createUnit().getClass(), Cavalryman.class);
    }

    @Test
    public void testCreateCavalrymanUnit() {
        unitsCreator = new CavalrymanCreator();
        assertEquals(unitsCreator.createUnit().getClass(), Cavalryman.class);
    }

    @Test
    public void testCreateSoldierUnit() {
        unitsCreator = new SoldierCreator();
        assertEquals(unitsCreator.createUnit().getClass(), Soldier.class);
    }
}