package org.jazzteam.martynchyk.entity.trade;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.City;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class TradeRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private City cityToTrade;

    public TradeRoute() {
    }

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
        TradeDealResult tradeResult = new TradeDealResult();
        tradeResult.setCityToTrade(cityToTrade);
        if (cityToTrade.agreeToTrade(tradeDeal)) {
            cityThatTrade.executeTradeDeal(tradeDeal, cityToTrade);
            tradeResult.setStatus(TradeDealResult.Status.SUCCESSFULLY);
            tradeResult.setAmountFrom(tradeDeal.getAmountFrom());
            tradeResult.setAmountTo(tradeDeal.getAmountTo());
        } else {
            tradeResult.setStatus(TradeDealResult.Status.REFUSED);
        }
        return tradeResult;
    }

    public City getCityToTrade() {
        return cityToTrade;
    }
}
