package org.jazzteam.martynchyk.entity;

public interface Combat {
    int getRange();

    int getRangedStrength();

    int getStrength();

    int getHealthPoint();

    //void setRange(int range);

    //void setRangedStrength(int rangedStrength);

    //void setStrength(int strength);

    void setHealthPoint(int healthPoint);

    default void fight(Combat enemy) {
        this.attack(enemy);
        enemy.attack(this);
    }

    default void attack(Combat enemy) {
        enemy.setHealthPoint(enemy.getHealthPoint() - calculateDamage(enemy));
    }

    default int calculateDamage(Combat enemy) {
        return (int) (30 * Math.exp((this.getStrength() - enemy.getStrength()) / 24.0));
    }

    default int calculateDamage(Combat enemy, int strengthIncrease) {
        return (int) (30 * Math.exp((this.getStrength() + strengthIncrease - enemy.getStrength()) / 24.0));
    }

    default boolean isDead() {
        return this.getHealthPoint() <= 0;
    }

}
