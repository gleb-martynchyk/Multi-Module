package org.jazzteam.martynchyk.dao.implementation;

import org.jazzteam.martynchyk.config.HibernateXMLConfig;
import org.jazzteam.martynchyk.config.UnitDaoConfig;
import org.jazzteam.martynchyk.entity.enums.ResourceType;
import org.jazzteam.martynchyk.entity.units.Settler;
import org.jazzteam.martynchyk.entity.units.Unit;
import org.jazzteam.martynchyk.entity.units.military.BaseWarrior;
import org.jazzteam.martynchyk.entity.units.military.Scout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateXMLConfig.class, UnitDaoConfig.class})
public class UnitDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UnitDao unitDao;

    @Test
    public void testCreateAndFindUnit() {
        Settler expectedSettler = new Settler();
        expectedSettler.setCostInGold(22);
        expectedSettler.setCostInResources(50);
        expectedSettler.setHealthPoint(100);
        expectedSettler.setMovement(1);
        expectedSettler.setResourceType(ResourceType.PRODUCTION);
        unitDao.create(expectedSettler);
        assertEquals(unitDao.find(expectedSettler.getId()), expectedSettler);
    }

    @Test
    public void testCreateAndFindUnitInheritorBaseWarrior() {
        BaseWarrior expectedScout = new Scout();

        unitDao.create(expectedScout);
        assertEquals(unitDao.find(expectedScout.getId()), expectedScout);
    }

    @Test
    public void testFindUnitDoesntExist() {
        Settler expectedSettler = new Settler();
        assertNull(unitDao.find(expectedSettler.getId()));
    }

    @Test
    public void testFindUnitWithNegativeId() {
        assertNull(unitDao.find(-1));
    }


    @Test
    public void testCreateUnit() {
        Settler expectedSettler = new Settler();
        unitDao.create(expectedSettler);
        assertEquals(unitDao.find(expectedSettler.getId()), expectedSettler);
    }


    @Test
    public void testFindAllUnits() {
        Unit settlerA = new Settler();
        Unit settlerB = new Settler();
        Unit settlerC = new Settler();

        unitDao.create(settlerA);
        unitDao.create(settlerB);
        unitDao.create(settlerC);

        List<Unit> unitsExpected = new ArrayList<>();
        unitsExpected.add(settlerA);
        unitsExpected.add(settlerB);
        unitsExpected.add(settlerC);

        assertEquals(unitDao.findAll(), unitsExpected);
    }

    @Test
    public void testFindAllUnitsIfEmpty() {
        assertTrue(unitDao.findAll().isEmpty());
    }
}