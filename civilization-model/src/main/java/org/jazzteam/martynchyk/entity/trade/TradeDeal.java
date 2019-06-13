package org.jazzteam.martynchyk.entity.trade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDeal {

    private Class resourceFrom;
    private Class resourceTo;
    private int amountFrom;
    private int amountTo;

    public TradeDeal(Class resourceFrom, int amountFrom, Class resourceTo, int amountTo) {
        this.resourceFrom = resourceFrom;
        this.resourceTo = resourceTo;
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
    }
}
