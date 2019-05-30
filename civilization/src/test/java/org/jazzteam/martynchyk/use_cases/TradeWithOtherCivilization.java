package org.jazzteam.martynchyk.use_cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Civilization;
import org.jazzteam.martynchyk.entity.resources.implementation.Food;
import org.jazzteam.martynchyk.entity.resources.implementation.Production;
import org.jazzteam.martynchyk.entity.trade.TradeDeal;
import org.jazzteam.martynchyk.entity.trade.TradeDealResult;
import org.jazzteam.martynchyk.entity.trade.TradeRoute;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TradeWithOtherCivilization {

    private Civilization civilizationA;
    private Civilization civilizationB;
    private TradeRoute tradeRoute;
    private TradeDealResult tradeDealResult;
    private TradeDeal tradeDeal;
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
    public void testAgreeToTrade() {
        cityA.getResources().get(Production.class).setAmount(20);
        cityA.getResources().get(Food.class).setAmount(20);

        tradeDeal = new TradeDeal(Production.class, Food.class, 2, 2);
        assertFalse(cityA.agreeToTrade(tradeDeal));
    }

    @Test
    public void testAgreeToTradOneCityHasFlaw() {
        cityB.getResources().get(Production.class).setAmount(10);
        cityB.getResources().get(Food.class).setAmount(20);

        tradeDeal = new TradeDeal(Production.class, Food.class, 2, 2);
        assertTrue(cityB.agreeToTrade(tradeDeal));
    }

    @Test
    public void testTrade() {
        cityA.getResources().get(Production.class).setAmount(20);
        cityA.getResources().get(Food.class).setAmount(3);

        cityB.getResources().get(Production.class).setAmount(10);
        cityB.getResources().get(Food.class).setAmount(20);

        cityA.addTradeRoute(new TradeRoute(cityB));

        tradeDeal = new TradeDeal(Production.class, Food.class, 2, 2);
        tradeDealResult = cityA.trade(tradeDeal);
        //TODO чтобы возвращало нормальный результат торгов

        assertTrue(
                cityA.getResources().get(Production.class).getAmount() == 18
                        && cityA.getResources().get(Food.class).getAmount() == 5
                        && cityB.getResources().get(Production.class).getAmount() == 12
                        && cityB.getResources().get(Food.class).getAmount() == 18
        );
    }

    @Test
    public void testTradeWhenOtherSideDoesNotAgree() {
        cityA.getResources().get(Production.class).setAmount(20);
        cityA.getResources().get(Food.class).setAmount(20);

        cityB.getResources().get(Production.class).setAmount(20);
        cityB.getResources().get(Food.class).setAmount(20);

        tradeDeal = new TradeDeal(Production.class, Food.class, 2, 2);
        tradeDealResult = cityA.trade(tradeDeal);

        assertTrue(
                cityA.getResources().get(Production.class).getAmount() == 8
                        && cityA.getResources().get(Food.class).getAmount() == 12
                        && cityB.getResources().get(Production.class).getAmount() == 12
                        && cityB.getResources().get(Food.class).getAmount() == 8
        );
    }

    @Test
    public void testExchangeResourcesThroughTradeRoute() {
        tradeDeal = new TradeDeal(Production.class, Food.class, 10, 30);
        tradeDealResult = cityA.trade(tradeDeal);
    }

    @Test
    public void testExecuteTradeDeal() {
        cityA.getResources().get(Production.class).setAmount(10);
        cityA.getResources().get(Food.class).setAmount(10);

        cityB.getResources().get(Production.class).setAmount(10);
        cityB.getResources().get(Food.class).setAmount(10);

        tradeDeal = new TradeDeal(Production.class, Food.class, 2, 2);
        cityA.executeTradeDeal(tradeDeal, cityB);

        assertTrue(
                cityA.getResources().get(Production.class).getAmount() == 8
                        && cityA.getResources().get(Food.class).getAmount() == 12
                        && cityB.getResources().get(Production.class).getAmount() == 12
                        && cityB.getResources().get(Food.class).getAmount() == 8
        );
    }
}
