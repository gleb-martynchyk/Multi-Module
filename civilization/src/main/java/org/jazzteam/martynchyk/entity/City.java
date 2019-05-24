package org.jazzteam.martynchyk.entity;

import lombok.Data;
import org.jazzteam.martynchyk.entity.building.Building;
import org.jazzteam.martynchyk.entity.building.Improvement;
import org.jazzteam.martynchyk.entity.building.Producing;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.units.Unit;

import java.util.HashSet;
import java.util.Set;

@Data
public class City implements Combat, Time {

    private Civilization civilization;
    private double defence;
    private double healthPoint;
    private int strength;
    private int level;
    private int food;
    private int production;
    private ReligionType dominantReligion;
    private Set<Unit> units;
    private Set<Building> buildings;

    public City(Civilization civilization) {
        this.civilization = civilization;
        this.healthPoint = 100;
        this.strength = 40;
        this.level = 0;
        this.defence = 20;
        this.dominantReligion = null;
        this.food = 10;
        this.production = 10;
        this.units = new HashSet<>();
        this.buildings = new HashSet<>();
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public int getRangedStrength() {
        return 0;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public double getDefence() {
        return defence;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    @Override
    public void doTick() {
        setFood(getFood() - 2 * units.size());
        collectResources();
    }

    public void addBuilding(Building building) {
        if (building instanceof Improvement) {
            ((Improvement) building).improveAttribute(this);
        }
        buildings.add(building);
    }

    public void removeBuilding(Building building) {
        if (building instanceof Improvement) {
            ((Improvement) building).afterRemoving(this);
        }
        buildings.remove(building);
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
    }

    public void collectResources() {
        buildings.stream()
                .filter(Producing.class::isInstance)
                .map(Producing.class::cast)
                .forEach(building -> building.produceResource(this));
    }

    @Override
    public String toString() {
        return "City{" +
                "civilization=" + civilization +
                ", defence=" + defence +
                ", healthPoint=" + healthPoint +
                ", strength=" + strength +
                ", level=" + level +
                ", food=" + food +
                ", production=" + production +
                ", dominantReligion=" + dominantReligion +
                ", units=" + units +
                ", buildings=" + buildings +
                '}';
    }
}
