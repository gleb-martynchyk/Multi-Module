package org.jazzteam.martynchyk.entity.trade;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.City;

@Getter
@Setter
public class TradeDealResult {

    private City cityThatTrade;
    private City cityToTrade;
    private int amountFrom;
    private int amountTo;
    private Status status;

    public enum Status {
        SUCCESSFULLY,
        CREATED,
        NOT_FULLY,
        REFUSED
    }

    public TradeDealResult() {
        this.status = Status.CREATED;
    }
}
