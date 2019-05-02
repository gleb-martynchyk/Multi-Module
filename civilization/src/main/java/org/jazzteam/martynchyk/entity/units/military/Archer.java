package org.jazzteam.martynchyk.entity.units.military;

import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.enums.ResourceType;

public class Archer extends BaseWarrior {
    public Archer() {
        super(10, 10, ResourceType.PRODUCTION,
                0, 2, 25, 15);
    }

    @Override
    public void attack(Combat enemy) {
        enemy.setHealthPoint(enemy.getHealthPoint() - calculateDamage(enemy));
    }
}