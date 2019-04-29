package org.jazzteam.martynchyk.dao;

import org.jazzteam.martynchyk.config.HibernateXMLConfig;
import org.jazzteam.martynchyk.config.UnitDaoConfig;
import org.jazzteam.martynchyk.entity.units.Settler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
@ContextConfiguration(classes = {HibernateXMLConfig.class, UnitDaoConfig.class})
public class UnitDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UnitDao unitDao;

    @Test
    public void testGetUnit() {
        Settler expectedSettler = new Settler();
        expectedSettler.setId(new Long(1));
        unitDao.create(expectedSettler);
        assertEquals(unitDao.get(new Long(1)), expectedSettler);
    }
}