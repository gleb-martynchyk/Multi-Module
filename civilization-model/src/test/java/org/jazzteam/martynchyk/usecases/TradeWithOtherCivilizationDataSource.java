package org.jazzteam.martynchyk.usecases;

import org.jazzteam.martynchyk.entity.resources.implementation.Food;
import org.jazzteam.martynchyk.entity.resources.implementation.Production;
import org.jazzteam.martynchyk.entity.trade.TradeDeal;
import org.jazzteam.martynchyk.entity.trade.TradeDealResult;
import org.testng.annotations.DataProvider;

public class TradeWithOtherCivilizationDataSource {

    @DataProvider(name = "TradeDealAndResults")
    public Object[][] getTradeDealAndResults() {
        return new Object[][]{
//        int cityAResourceFrom, int cityAResourceTo, - CITY THAT TRADE START PARAMETERS
//        int cityBResourceFrom, int cityBResourceTo, - CITY TO TRADE
//        TradeDeal deal, TradeDealResult result
                {
                        10, 10,
                        10, 10,
                        new TradeDeal(Production.class, 2, Food.class, 2),
                        new TradeDealResult(0, 0, TradeDealResult.Status.REFUSED)
                },
                {
                        55, 55,
                        55, 55,
                        new TradeDeal(Production.class, 2, Food.class, 2),
                        new TradeDealResult(0, 0, TradeDealResult.Status.REFUSED)
                },
                {
                        20, 20,
                        10, 20,
                        new TradeDeal(Production.class, 2, Food.class, 2),
                        new TradeDealResult(2, 2, TradeDealResult.Status.SUCCESSFULLY)
                },
                {
                        33, 22,
                        40, 50,
                        new TradeDeal(Production.class, 2, Food.class, 2),
                        new TradeDealResult(2, 2, TradeDealResult.Status.SUCCESSFULLY)
                },
        };
    }

    @DataProvider(name = "ExchangeParameters")
    public Object[][] getExecuteTradeDealParameters() {
        return new Object[][]{
                {
                        20, 20,
                        10, 20,
                        new TradeDeal(Production.class, 2, Food.class, 2)
                },
                {
                        33, 22,
                        40, 50,
                        new TradeDeal(Production.class, 2, Food.class, 2)
                },
        };
    }
}
