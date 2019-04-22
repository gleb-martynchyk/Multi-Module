package org.jazzteam.martynchyk.entity;

import org.jazzteam.martynchyk.entity.building.Building;
import org.jazzteam.martynchyk.entity.units.Unit;

import java.util.List;

public class City {
    private int healthPoint;
    private int level;
    private ReligionsType dominantReligios;
    private CityResources cityResources;
    private List<Unit> units;
    private List<Building> buildings;
}
