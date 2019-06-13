package org.jazzteam.martynchyk.entity.resources.implementation;

import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.resources.Resource;

import javax.persistence.Entity;

@Entity
public class Food extends Resource {

    public Food() {
    }

    public Food(int amount) {
        super(amount);
    }

    public boolean isInAbundance(City city) {
        return getExcess(city) > 0;
    }

    @Override
    public int getExcess(City city) {
        return getAmount() - (int) (2 * city.getUnits().size() * 1.1) - MIN;
    }
}
