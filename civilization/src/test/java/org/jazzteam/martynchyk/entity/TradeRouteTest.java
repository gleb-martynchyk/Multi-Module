package org.jazzteam.martynchyk.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.use_cases.BattleWithTheCityTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

public class TradeRouteTest {

    private Civilization civilizationA;
    private Civilization civilizationB;
    private TradeRoute tradeRoute;
    private City cityA;
    private City cityB;
    private Logger log = LogManager.getLogger(BattleWithTheCityTest.class);

    @BeforeMethod
    public void setUp() {
        civilizationA = new Civilization();
        civilizationB = new Civilization();
        cityA = new City(civilizationA);
        cityB = new City(civilizationB);
    }

    @Test
    public void testCreateTradeRouteNull() {
        cityA.setCivilization(civilizationA);
        cityB.setCivilization(civilizationA);
        tradeRoute = TradeRoute.createTradeRoute(cityA, cityB);
        assertNull(tradeRoute);
    }

    @Test
    public void testCreateTradeRouteNotNull() {
        tradeRoute = TradeRoute.createTradeRoute(cityA, cityB);
        assertNotNull(tradeRoute);
    }

    @Test
    public void testCreateTradeRoute() {
        tradeRoute = TradeRoute.createTradeRoute(cityA, cityB);
        assertTrue(tradeRoute.getCityFirst().equals(cityA)&&tradeRoute.getCitySecond().equals(cityB));
    }

    @Test
    public void testGetCityToTrade() {
    }
}