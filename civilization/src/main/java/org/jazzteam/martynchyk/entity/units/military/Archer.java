package org.jazzteam.martynchyk.entity.units.military;

import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.enums.ResourceType;

public class Archer extends BaseWarrior {
    public Archer() {
        super(10, 10, ResourceType.PRODUCTION,
                0, 2, 25, 15);
    }

    @Override
    public void fight(Combat enemy) {
        this.attack(enemy);
    }

    @Override
    public double calculateDamage(Combat enemy) {
        return (30 * Math.exp((this.getRangedStrength() - enemy.getStrength()) / 24.0)) / (enemy.getDefence() * 0.1);
    }

    @Override
    public String toString() {
        return "Archer " + super.toString();
    }
}