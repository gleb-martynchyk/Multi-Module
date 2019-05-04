package org.jazzteam.martynchyk.entity;

import lombok.Data;
import org.jazzteam.martynchyk.entity.building.Building;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.units.Unit;

import java.util.List;

@Data
public class City implements Combat {
    private double healthPoint;
    private int strength;
    private int level;
    private double defence;
    private ReligionType dominantReligion;
    private CityResources cityResources;
    private List<Unit> units;
    private List<Building> buildings;

    public City() {
        this.healthPoint = 100;
        this.strength = 50;
        this.level = 0;
        this.defence = 20;
        this.dominantReligion = null;
        this.cityResources = null;
        this.units = null;
        this.buildings = null;
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
        return defence*0.1;
    }

    @Override
    public String toString() {
        return "City{" +
                "healthPoint=" + healthPoint +
                ", strength=" + strength +
                ", level=" + level +
                ", defence=" + defence +
                ", dominantReligion=" + dominantReligion +
                ", cityResources=" + cityResources +
                ", units=" + units +
                ", buildings=" + buildings +
                '}';
    }
}
