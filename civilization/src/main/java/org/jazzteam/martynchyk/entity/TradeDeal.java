package org.jazzteam.martynchyk.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDeal {

    private String resourceFrom;
    private String resourceTo;
    private int expectedAmountFrom;
    private int expectedAmountTo;
    private int actualAmountFrom;
    private int actualAmountTo;
    private Status status;

    public enum Status {
        SUCCESSFULLY,
        CREATED,
        NOT_FULLY,
        REFUSED
    }

    public TradeDeal(String resourceFrom, String resourceTo, int amountFrom, int amountTo) {
        this.resourceFrom = resourceFrom;
        this.resourceTo = resourceTo;
        this.expectedAmountFrom = amountFrom;
        this.expectedAmountTo = amountTo;
        this.status = Status.CREATED;
    }
}
