package org.jazzteam.martynchyk.entity.units.military;

import org.jazzteam.martynchyk.entity.enums.ResourceType;

import javax.persistence.Entity;

public class Warrior extends BaseWarrior {
    public Warrior() {
        super(10, 10, ResourceType.PRODUCTION,
                1, 0, 0, 20);
    }

    @Override
    public String toString() {
        return "Warrior " + super.toString();
    }

}
