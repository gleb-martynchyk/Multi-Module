package org.jazzteam.martynchyk.entity;

import lombok.Data;
import org.jazzteam.martynchyk.entity.building.Building;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.units.Unit;

import java.util.List;

@Data
public class City implements Combat {
    private int healthPoint;
    private int strength;
    private int level;
    private ReligionType dominantReligion;
    private CityResources cityResources;
    private List<Unit> units;
    private List<Building> buildings;

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
        return 0;
    }
}
