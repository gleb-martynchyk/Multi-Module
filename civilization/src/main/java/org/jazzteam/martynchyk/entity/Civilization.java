package org.jazzteam.martynchyk.entity;

import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.tree.Tree;

import java.util.List;

public class Civilization implements Time {
    private int faith;
    private int gold;
    private int scince;
    private List<City> cities;
    private ReligionType dominantReligios;
    private Tree scinceTree;

    @Override
    public void doTick() {
        cities.stream()
                .forEach(city -> city.doTick());
    }
}
