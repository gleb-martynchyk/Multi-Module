package org.jazzteam.martynchyk.entity;

import lombok.Data;
import org.jazzteam.martynchyk.entity.enums.ReligionType;
import org.jazzteam.martynchyk.entity.tree.Tree;

import java.util.ArrayList;
import java.util.List;

@Data
public class Civilization implements Time {
    private int faith;
    private int gold;
    private int science;
    private List<City> cities;
    private ReligionType dominantReligios;
    private Tree scienceTree;

    public Civilization() {
        this.faith = 0;
        this.gold = 0;
        this.science = 0;
        this.cities = new ArrayList<>();
    }

    @Override
    public void doTick() {
        cities.stream()
                .forEach(city -> city.doTick());
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public void removeCity(City city) {
        cities.add(city);
    }

}
