package org.jazzteam.martynchyk.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeRoute {
    private City cityFirst;
    private City citySecond;

    private TradeRoute(City cityFirst, City citySecond) {
        this.cityFirst = cityFirst;
        this.citySecond = citySecond;
    }

    public static TradeRoute createTradeRoute(City cityFirst, City citySecond) {
        if (!cityFirst.getCivilization().equals(citySecond.getCivilization())) {
            TradeRoute tradeRoute = new TradeRoute(cityFirst, citySecond);
            cityFirst.addTradeRoute(tradeRoute);
            citySecond.addTradeRoute(tradeRoute);
            return tradeRoute;
        } else {
            return null;
        }
    }

    public City getCityToTrade(City cityThatTrade) {
        if (cityThatTrade.equals(cityFirst)) {
            return citySecond;
        } else {
            return cityFirst;
        }
    }
}
