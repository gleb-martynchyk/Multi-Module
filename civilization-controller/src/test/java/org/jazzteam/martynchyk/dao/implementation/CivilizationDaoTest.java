package org.jazzteam.martynchyk.dao.implementation;

import org.jazzteam.martynchyk.config.CivilizationDaoConfig;
import org.jazzteam.martynchyk.config.HibernateXMLConfig;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.resources.implementation.Food;
import org.jazzteam.martynchyk.entity.units.Settler;
import org.jazzteam.martynchyk.entity.units.Trader;
import org.jazzteam.martynchyk.entity.units.Worker;
import org.jazzteam.martynchyk.entity.units.military.Archer;
import org.jazzteam.martynchyk.entity.units.military.Scout;
import org.jazzteam.martynchyk.entity.units.military.Spearman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateXMLConfig.class, CivilizationDaoConfig.class})
public class CivilizationDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private CivilizationDao civilizationDao;

    @Test
//    @Rollback(false)
    public void testCreateAndFindCivilization() {
        Civilization expectedCivilization = new Civilization();
        expectedCivilization.setName("Russia");

        City moscow = new City(expectedCivilization);
        moscow.setName("Moscow");
        expectedCivilization.addCity(moscow);
        expectedCivilization.setCapital(moscow);

        City samara = new City(expectedCivilization);
        samara.setName("Samara");
        expectedCivilization.addCity(samara);

        civilizationDao.create(expectedCivilization);
        assertEquals(civilizationDao.find(expectedCivilization.getId()), expectedCivilization);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void testCreateCivilizationWithDuplicateName() {
        Civilization russia = new Civilization();
        russia.setName("Russia");
        civilizationDao.create(russia);

        Civilization russiaDuplicate = new Civilization();
        russiaDuplicate.setName("Russia");
        civilizationDao.create(russiaDuplicate);
    }

    @Test(testName = "CreateAndSaveCivilization")
//    @Rollback(false)
    public void testCreateCivilizationRussia() {
        Civilization expectedCivilization = new Civilization();
        expectedCivilization.setName("Russia");

        City moscow = new City(expectedCivilization);
        moscow.setName("Moscow");
        expectedCivilization.addCity(moscow);
        expectedCivilization.setCapital(moscow);

        City samara = new City(expectedCivilization);
        samara.setName("Samara");
        expectedCivilization.addCity(samara);

        moscow.getResources().get(Food.class).setAmount(50);
        samara.getResources().get(Food.class).setAmount(50);

        moscow.addUnit(new Settler());
        moscow.addUnit(new Trader());
        moscow.addUnit(new Worker());

        samara.addUnit(new Archer());
        samara.addUnit(new Spearman());
        samara.addUnit(new Scout());

        civilizationDao.create(expectedCivilization);

        Civilization actualCivilization = civilizationDao.find(expectedCivilization.getId());

        assertEquals(actualCivilization, expectedCivilization);
    }

    @Test(dependsOnMethods = "testCreateCivilizationRussia")
    @Ignore
    public void testFindCivilizationRussia() {
        Civilization actualCivilization = civilizationDao.find(1);
        assertNotNull(actualCivilization);
    }


    @Test
//    @Rollback(false)
    public void testDoTickRemovingUnitFromCityWhenCalled() {
        Civilization expectedCivilization = new Civilization();
        expectedCivilization.setName("Russia");

        City moscow = new City(expectedCivilization);
        moscow.setName("Moscow");
        expectedCivilization.addCity(moscow);
        expectedCivilization.setCapital(moscow);

        City samara = new City(expectedCivilization);
        samara.setName("Samara");
        expectedCivilization.addCity(samara);

        moscow.addUnit(new Settler());
        moscow.addUnit(new Trader());
        moscow.addUnit(new Trader());
        samara.addUnit(new Worker());
        samara.addUnit(new Scout());
        samara.addUnit(new Scout());

        civilizationDao.create(expectedCivilization);

        Civilization civilization = civilizationDao.find(expectedCivilization.getId());
        int unitsCountExpected = civilization.getCities().get(0).getUnits().size() - 1;

        civilization.getCities().get(0).getUnits().get(0).setHealthPoint(-20);

        civilization.doTick();
        civilizationDao.update(civilization);

        Civilization actualCivilization = civilizationDao.find(expectedCivilization.getId());
        int unitsCountActual = actualCivilization.getCities().get(0).getUnits().size();

        assertEquals(unitsCountActual, unitsCountExpected);
    }

    @Test
    public void testFindCivilizationWhichDoesntExist() {
        Civilization expectedCivilization = new Civilization();
        expectedCivilization.setName("Russia");
        assertNull(civilizationDao.find(expectedCivilization.getId()));
    }

    @Test
    public void testFindCivilizationWithNegativeId() {
        assertNull(civilizationDao.find(-1));
    }


    @Test
    public void testCreateCivilization() {
        Civilization expectedCivilization = new Civilization();
        civilizationDao.create(expectedCivilization);
        assertEquals(civilizationDao.find(expectedCivilization.getId()), expectedCivilization);
    }


    @Test
    public void testFindAllCivilizations() {
        Civilization settlerA = new Civilization();
        Civilization settlerB = new Civilization();
        Civilization settlerC = new Civilization();

        civilizationDao.create(settlerA);
        civilizationDao.create(settlerB);
        civilizationDao.create(settlerC);

        List<Civilization> unitsExpected = new ArrayList<>();
        unitsExpected.add(settlerA);
        unitsExpected.add(settlerB);
        unitsExpected.add(settlerC);

        assertEquals(civilizationDao.findAll(), unitsExpected);
    }

    @Test
    public void testFindAllCivilizationsIfEmpty() {
        assertTrue(civilizationDao.findAll().isEmpty());
    }
}