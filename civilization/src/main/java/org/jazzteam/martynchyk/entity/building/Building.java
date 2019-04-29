package org.jazzteam.martynchyk.entity.building;

import lombok.Data;

@Data
public abstract class Building {
    private int slots;
    private int productionCost;
}
