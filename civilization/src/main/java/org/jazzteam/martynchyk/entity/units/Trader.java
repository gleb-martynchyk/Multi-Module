package org.jazzteam.martynchyk.entity.units;

import lombok.Data;
import org.jazzteam.martynchyk.entity.TradeConnection;

@Data
public class Trader extends Unit {
    private TradeConnection tradeConnection;
}
