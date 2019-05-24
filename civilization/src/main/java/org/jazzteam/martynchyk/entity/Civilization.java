package org.jazzteam.martynchyk.entity;

import lombok.Data;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.tree.Tree;

import java.util.List;

@Data
public class Civilization implements Time {
    private int faith;
    private int gold;
    private int science;
    private List<City> cities;
    private ReligionType dominantReligios;
    private Tree scienceTree;

    @Override
    public void doTick() {
        cities.stream()
                .forEach(city -> city.doTick());
    }
}
