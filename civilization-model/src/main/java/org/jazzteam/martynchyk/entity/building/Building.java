package org.jazzteam.martynchyk.entity.building;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Building {

    private int productionCost;

    public Building() {
    }

    public Building(int productionCost) {
        this.productionCost = productionCost;
    }
}
