package org.jazzteam.martynchyk.entity.units.military;

import org.jazzteam.martynchyk.entity.enums.ResourceType;

public class Scout extends BaseWarrior {
    public Scout() {
        super(10, 10, ResourceType.PRODUCTION,
                0, 0, 0, 0);
    }
}
