package org.jazzteam.martynchyk.entity.building;

import org.jazzteam.martynchyk.entity.City;

public class Barrack extends Building implements Improvement {

    private static final int strengthImprovePoint = 10;

    public Barrack() {
    }

    @Override
    public void afterRemoving(City city) {
        city.setStrength(city.getStrength() - strengthImprovePoint);
    }

    @Override
    public void improveAttribute(City city) {
        city.setStrength(city.getStrength() + strengthImprovePoint);
    }
}
