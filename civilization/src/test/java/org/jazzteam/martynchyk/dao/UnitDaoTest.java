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
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes = {HibernateXMLConfig.class, UnitDaoConfig.class})
public class UnitDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UnitDao unitDao;

    @Test
    @Rollback
    public void testGetUnit() {
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
}