package org.jazzteam.martynchyk.entity.trade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDealResult {

    private int actualAmountFrom;
    private int actualAmountTo;
    private Status status;

    public enum Status {
        SUCCESSFULLY,
        CREATED,
        NOT_FULLY,
        REFUSED
    }

    public TradeDealResult() {
        this.status = Status.CREATED;
//        this.actualAmountFrom = 0;
//        this.actualAmountTo = 0;
    }
}
