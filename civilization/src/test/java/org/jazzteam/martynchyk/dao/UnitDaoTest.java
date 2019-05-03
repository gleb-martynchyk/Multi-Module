package org.jazzteam.martynchyk.dao;

import org.jazzteam.martynchyk.config.HibernateXMLConfig;
import org.jazzteam.martynchyk.config.UnitDaoConfig;
import org.jazzteam.martynchyk.dao.implementation.UnitDao;
import org.jazzteam.martynchyk.entity.units.Settler;
import org.jazzteam.martynchyk.entity.units.Unit;
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

@WebAppConfiguration
@ContextConfiguration(classes = {HibernateXMLConfig.class, UnitDaoConfig.class})
public class UnitDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UnitDao unitDao;


    @Test(dependsOnGroups = "b")
    public void testCreateAndFindUnit() {
        Settler expectedSettler = new Settler();
        unitDao.create(expectedSettler);
        assertEquals(unitDao.find(expectedSettler.getId()), expectedSettler);
    }

    @Test(dependsOnGroups = "b")
    public void testFindUnitDoesntExist() {
        Settler expectedSettler = new Settler();
        assertNull(unitDao.find(expectedSettler.getId()));
    }

    @Test(dependsOnGroups = "b")
    public void testFindUnitWithNegativeId() {
        assertNull(unitDao.find(-1));
    }


    @Test(dependsOnGroups = "b")
    @Rollback
    @Transactional
    public void testCreateUnit() {
        Settler expectedSettler = new Settler();
        unitDao.create(expectedSettler);
        assertEquals(unitDao.find(expectedSettler.getId()), expectedSettler);
    }


    @Test(dependsOnGroups = "a", groups = "b")
    @Rollback
    @Transactional
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

    @Test(groups = "a")
    public void testFindAllUnitsIfEmpty() {
        assertTrue(unitDao.findAll().isEmpty());
    }
}