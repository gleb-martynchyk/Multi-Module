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

    default void attack(Combat enemy) {
        enemy.setHealthPoint(enemy.getHealthPoint() - calculateDamage(enemy));
        if (enemy.getHealthPoint() > 0) {
            this.setHealthPoint(this.getHealthPoint() - calculateDamage(this));
        }
    }

    default int calculateDamage(Combat enemy) {
        return (int) (30 * Math.exp((this.getStrength() - enemy.getStrength()) / 24.0));
    }

    default boolean isDead() {
        return this.getHealthPoint() <= 0;
    }

}
