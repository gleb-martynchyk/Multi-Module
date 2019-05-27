package org.jazzteam.martynchyk.entity.units.military;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.enums.ResourceType;
import org.jazzteam.martynchyk.entity.units.Unit;

import java.util.Objects;

@Getter
@Setter
public abstract class BaseWarrior extends Unit implements Combat {
    private int range;
    private int rangedStrength;
    private int strength;

    public BaseWarrior(int costInGold, int costInResources, ResourceType resourceType,
                       int movement, int range, int rangedStrength, int strength) {
        super(costInGold, costInResources, resourceType, movement);
        this.range = range;
        this.rangedStrength = rangedStrength;
        this.strength = strength;
    }

    public void laySiege(City city) {
        city.addBesiegeUnit(this);
    }

    @Override
    public String toString() {
        return "range=" + range +
                ", rangedStrength=" + rangedStrength +
                ", strength=" + strength +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseWarrior warrior = (BaseWarrior) o;
        return range == warrior.range &&
                rangedStrength == warrior.rangedStrength &&
                strength == warrior.strength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), range, rangedStrength, strength);
    }
}
