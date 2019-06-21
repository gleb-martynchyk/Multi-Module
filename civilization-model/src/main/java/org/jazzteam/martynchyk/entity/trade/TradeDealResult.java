package org.jazzteam.martynchyk.entity.trade;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.City;

import java.util.Objects;

@Getter
@Setter
@Data
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

    public TradeDealResult(int amountFrom, int amountTo, Status status) {
        this.amountFrom = amountFrom;
        this.amountTo = amountTo;
        this.status = status;
    }
}
