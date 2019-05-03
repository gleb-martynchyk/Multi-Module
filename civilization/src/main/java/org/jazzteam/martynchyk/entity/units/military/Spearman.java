package org.jazzteam.martynchyk.entity.units.military;

import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.enums.ResourceType;

public class Spearman extends BaseWarrior {
    public Spearman() {
        super(10, 10, ResourceType.PRODUCTION,
                0, 1, 0, 25);
    }

    @Override
    public void attack(Combat enemy) {
        int strengthIncrease = 0;
        if (enemy instanceof HorseMan) {
            strengthIncrease += 10;
        }
        enemy.setHealthPoint(enemy.getHealthPoint() - calculateDamage(enemy, strengthIncrease));
    }
}
