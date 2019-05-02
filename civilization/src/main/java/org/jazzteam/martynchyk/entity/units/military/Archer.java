package org.jazzteam.martynchyk.entity.units.military;

import org.jazzteam.martynchyk.entity.Combat;

public class Archer extends BaseWarrior {
    @Override
    public void attack(Combat enemy) {
        enemy.setHealthPoint(enemy.getHealthPoint() - calculateDamage(enemy));
    }
}