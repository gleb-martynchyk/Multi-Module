package org.jazzteam.martynchyk.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.trade.TradeRoute;
import org.jazzteam.martynchyk.usecases.BattleWithTheCityTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
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
        civilizationA.addCity(cityA);
        civilizationB.addCity(cityB);
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
        assertTrue(cityA.getTradeRoutes().contains(tradeRoute));
    }

    @Test
    public void testCreateTradeRouteOneDirectional() {
        tradeRoute = TradeRoute.createTradeRoute(cityA, cityB);
        assertTrue(cityA.getTradeRoutes().contains(tradeRoute)
                && cityB.getTradeRoutes().isEmpty());
    }

    @Test
    public void testGetCityToTrade() {
        tradeRoute = TradeRoute.createTradeRoute(cityA, cityB);
        assertEquals(cityB, tradeRoute.getCityToTrade());
    }


}