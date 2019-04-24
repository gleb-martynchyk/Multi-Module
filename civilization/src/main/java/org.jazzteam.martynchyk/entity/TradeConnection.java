package org.jazzteam.martynchyk.entity;

import lombok.Data;
import org.jazzteam.martynchyk.entity.units.Trader;

@Data
public class TradeConnection {
    private City firsCity;
    private City secondCity;
    private Trader trader;
}
