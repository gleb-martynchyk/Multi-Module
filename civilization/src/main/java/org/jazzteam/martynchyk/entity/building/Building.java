package org.jazzteam.martynchyk.entity.building;

import lombok.Data;
import org.jazzteam.martynchyk.entity.City;

@Data
public abstract class Building {
    private int slots;
    private int productionCost;

    public void produceResource(City city) {

    }
}
