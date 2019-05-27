package org.jazzteam.martynchyk.entity;

public interface Combat {
    int getRange();

    int getRangedStrength();

    int getStrength();

    double getHealthPoint();

    default double getDefence() {
        return 10;
    }

    void setHealthPoint(double healthPoint);

    default void fight(Combat enemy) {
        this.attack(enemy);
        enemy.attack(this);
    }

    default void attack(Combat enemy) {
        enemy.setHealthPoint(enemy.getHealthPoint() - calculateDamage(enemy));
    }

    default double calculateDamage(Combat enemy) {
        return (30 * Math.exp((this.getStrength() - enemy.getStrength()) / 24.0)) / (enemy.getDefence() * 0.1);
    }

    default double calculateDamage(Combat enemy, int strengthIncrease) {
        return (30 * Math.exp((this.getStrength() + strengthIncrease - enemy.getStrength()) / 24.0));
    }

    default boolean isDead() {
        return this.getHealthPoint() <= 0;
    }

}
