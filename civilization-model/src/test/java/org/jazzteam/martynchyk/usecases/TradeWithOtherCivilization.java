package org.jazzteam.martynchyk.usecases;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class TradeWithOtherCivilization {

    private Civilization civilizationA;
    private Civilization civilizationB;
    private TradeRoute tradeRoute;
    private TradeDealResult tradeDealResult;
    private TradeDeal tradeDeal;
    private City london;
    private City tokyo;
    private Logger log = LogManager.getLogger(BattleWithTheCityTest.class);

    @BeforeMethod
    public void setUp() {
        civilizationA = new Civilization();
        civilizationB = new Civilization();
        london = new City(civilizationA);
        tokyo = new City(civilizationB);
    }

    @Test
    public void testCityAgreeToTrade() {
        london.getResources().get(Production.class).setAmount(20);
        london.getResources().get(Food.class).setAmount(20);

        tradeDeal = new TradeDeal(Production.class, 2, Food.class, 2);
        assertFalse(london.agreeToTrade(tradeDeal));
    }

    @Test
    public void testAgreeToTradeOneCityHasFlaw() {
        tokyo.getResources().get(Production.class).setAmount(10);
        tokyo.getResources().get(Food.class).setAmount(20);

        tradeDeal = new TradeDeal(Production.class, 2, Food.class, 2);
        assertTrue(tokyo.agreeToTrade(tradeDeal));
    }

    @Test
    public void testTrade() {
        int cityAProduction = 20;
        int cityAFood = 20;

        int cityBProduction = 10;
        int cityBFood = 20;

        london.getResources().get(Production.class).setAmount(cityAProduction);
        london.getResources().get(Food.class).setAmount(cityAFood);

        tokyo.getResources().get(Production.class).setAmount(cityBProduction);
        tokyo.getResources().get(Food.class).setAmount(cityBFood);

        london.addTradeRoute(tokyo);

        int amountFrom = 2;
        int amountTo = 2;

        tradeDeal = new TradeDeal(Production.class, amountFrom, Food.class, amountTo);
        tradeDealResult = london.trade(tradeDeal);

        boolean resFromDecrease = london.getResources().get(Production.class).getAmount() == cityAProduction - amountFrom;
        boolean resFromIncrease = tokyo.getResources().get(Production.class).getAmount() == cityBProduction + amountFrom;
        boolean resToDecrease = tokyo.getResources().get(Food.class).getAmount() == cityBFood - amountTo;
        boolean resToIncrease = london.getResources().get(Food.class).getAmount() == cityBFood + amountTo;
        boolean tradeStatus = tradeDealResult.getStatus() == TradeDealResult.Status.SUCCESSFULLY;

        assertTrue(resFromDecrease && resFromIncrease && resToDecrease && resToIncrease && tradeStatus);
    }

    @Test
    public void testTradeDealStatus() {
        london.getResources().get(Production.class).setAmount(50);
        london.getResources().get(Food.class).setAmount(50);

        tokyo.getResources().get(Production.class).setAmount(50);
        tokyo.getResources().get(Food.class).setAmount(50);

        london.addTradeRoute(new TradeRoute(tokyo));

        int amountFrom = 2;
        int amountTo = 2;

        tradeDeal = new TradeDeal(Production.class, amountFrom, Food.class, amountTo);
        tradeDealResult = london.trade(tradeDeal);

        assertEquals(tradeDealResult.getStatus(), TradeDealResult.Status.REFUSED);
    }


    @Test
    public void testExecuteTradeDeal() {
        london.getResources().get(Production.class).setAmount(10);
        london.getResources().get(Food.class).setAmount(10);

        tokyo.getResources().get(Production.class).setAmount(10);
        tokyo.getResources().get(Food.class).setAmount(10);

        tradeDeal = new TradeDeal(Production.class, 2, Food.class, 2);
        london.executeTradeDeal(tradeDeal, tokyo);

        assertTrue(
                london.getResources().get(Production.class).getAmount() == 8
                        && london.getResources().get(Food.class).getAmount() == 12
                        && tokyo.getResources().get(Production.class).getAmount() == 12
                        && tokyo.getResources().get(Food.class).getAmount() == 8
        );
    }

    @Test(dataProvider = "ExchangeParameters", dataProviderClass = TradeWithOtherCivilizationDataSource.class)
    public void testExecuteTradeDealParametrize(
            int cityAResourceFrom, int cityAResourceTo, int cityBResourceFrom, int cityBResourceTo,
            TradeDeal deal
    ) {
        Class resourceFrom = deal.getResourceFrom();
        Class resourceTo = deal.getResourceTo();

        london.getResources().get(resourceFrom).setAmount(cityAResourceFrom);
        london.getResources().get(resourceTo).setAmount(cityAResourceTo);

        tokyo.getResources().get(resourceFrom).setAmount(cityBResourceFrom);
        tokyo.getResources().get(resourceTo).setAmount(cityBResourceTo);

        london.executeTradeDeal(deal, tokyo);

        assertEquals(cityAResourceFrom - deal.getAmountFrom(), london.getResources().get(resourceFrom).getAmount());
        assertEquals(cityAResourceTo + deal.getAmountTo(), london.getResources().get(resourceTo).getAmount());
        assertEquals(cityBResourceFrom + deal.getAmountFrom(), tokyo.getResources().get(resourceFrom).getAmount());
        assertEquals(cityBResourceTo - deal.getAmountTo(), tokyo.getResources().get(resourceTo).getAmount());
    }

    @Test(dataProvider = "TradeDealAndResults", dataProviderClass = TradeWithOtherCivilizationDataSource.class)
    public void testTradeParametrize(
            int cityAResourceFrom, int cityAResourceTo, int cityBResourceFrom, int cityBResourceTo,
            TradeDeal deal, TradeDealResult expectedResult
    ) {
        Class resourceFrom = deal.getResourceFrom();
        Class resourceTo = deal.getResourceTo();

        london.getResources().get(resourceFrom).setAmount(cityAResourceFrom);
        london.getResources().get(resourceTo).setAmount(cityAResourceTo);

        tokyo.getResources().get(resourceFrom).setAmount(cityBResourceFrom);
        tokyo.getResources().get(resourceTo).setAmount(cityBResourceTo);

        london.addTradeRoute(tokyo);
        tradeDealResult = london.trade(deal);

        assertEquals(expectedResult.getStatus(), tradeDealResult.getStatus());
        assertEquals(expectedResult.getAmountFrom(), tradeDealResult.getAmountFrom());
        assertEquals(expectedResult.getAmountTo(), tradeDealResult.getAmountTo());
    }
}
