package org.jazzteam.martynchyk.entity.units.military;

import lombok.Data;
import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.enums.ResourceType;
import org.jazzteam.martynchyk.entity.units.Unit;

@Data
public abstract class BaseWarrior extends Unit implements Combat {
    private int range;
    private int rangedStrength;
    private int strength;

    public BaseWarrior(int costInGold, int costInResources, ResourceType resourceType,
                       int movement, int range, int rangedStrength, int strength) {

        super(costInGold, costInResources, resourceType, movement);
        this.range = range;
        this.rangedStrength = rangedStrength;
        this.strength = strength;
    }
}
