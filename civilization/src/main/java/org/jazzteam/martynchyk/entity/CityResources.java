package org.jazzteam.martynchyk.entity;

import lombok.Data;

@Data
public class CityResources {
    private int food;
    private int production;

    public CityResources(int food, int production) {
        this.food = food;
        this.production = production;
    }
}
