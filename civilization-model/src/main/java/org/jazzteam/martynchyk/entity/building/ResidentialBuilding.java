package org.jazzteam.martynchyk.entity.building;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class ResidentialBuilding extends Building {
    private int slots;

    public ResidentialBuilding(int productionCost, int slots) {
        super(productionCost);
        this.slots = slots;
    }
}
