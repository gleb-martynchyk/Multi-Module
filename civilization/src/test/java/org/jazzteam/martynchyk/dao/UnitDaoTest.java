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
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateXMLConfig.class, UnitDaoConfig.class})
public class UnitDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UnitDao unitDao;

    @Rollback(true)
    @Test
    public void testFindUnit() {
        Settler expectedSettler = new Settler();
        unitDao.create(expectedSettler);
        assertEquals(unitDao.find(expectedSettler.getId()), expectedSettler);
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

    //TODO негативный тест для метода create
    @Test
    public void testCreateUnitWithSameId() {
        Settler settler = new Settler();
        Settler expectedSettler = new Settler();
        expectedSettler.setId(settler.getId());
        unitDao.create(settler);
        unitDao.create(expectedSettler);
        //assertEquals(unitDao.find(expectedSettler.getId()), expectedSettler);
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

    @Ignore
    @Test
    public void testFindAllUnitsSec() {
        Unit settlerA = new Settler();
        Unit settlerB = new Settler();

        unitDao.create(settlerA);
        unitDao.create(settlerB);

        List<Unit> unitsExpected = new ArrayList<>();
        unitsExpected.add(settlerA);
        unitsExpected.add(settlerB);

        assertEquals(unitDao.findAll(), unitsExpected);
    }

    @Test
    public void testFindAllUnitsIfEmpty() {
        assertNull(unitDao.findAll());
    }
}