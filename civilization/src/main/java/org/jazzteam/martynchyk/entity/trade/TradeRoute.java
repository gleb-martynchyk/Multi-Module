package org.jazzteam.martynchyk.entity.trade;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.City;

@Getter
@Setter
public class TradeRoute {

    private City cityToTrade;

    public TradeRoute(City cityToTrade) {
        this.cityToTrade = cityToTrade;
    }

    public static TradeRoute createTradeRoute(City cityThatTrade, City cityToTrade) {
        if (!cityThatTrade.getCivilization().equals(cityToTrade.getCivilization())) {
            TradeRoute tradeRoute = new TradeRoute(cityToTrade);
            cityThatTrade.addTradeRoute(tradeRoute);
            return tradeRoute;
        } else {
            return null;
        }
    }

    public TradeDealResult exchange(TradeDeal tradeDeal, City cityThatTrade) {
        if (cityToTrade.agreeToTrade(tradeDeal)) {
            cityThatTrade.executeTradeDeal(tradeDeal, cityToTrade);
        }
        return null;
    }

    public City getCityToTrade() {
        return cityToTrade;
    }
}
