package org.jazzteam.martynchyk.entity.units.military;

import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.enums.ResourceType;

public class HorseMan extends BaseWarrior {
    public HorseMan() {
        super(10, 10, ResourceType.PRODUCTION,
                2, 0, 0, 35);
    }

    @Override
    public String toString() {
        return "HorseMan " + super.toString();
    }
}
