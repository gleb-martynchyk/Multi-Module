package org.jazzteam.martynchyk.entity.building;

import org.jazzteam.martynchyk.entity.City;

public class DefensiveWall extends Building implements Improvement {

    private static final int defenseImprovePoint = 10;

    public DefensiveWall() {
    }

    @Override
    public void afterRemoving(City city) {
        city.setDefence(city.getDefence() - defenseImprovePoint);
    }

    @Override
    public void improveAttribute(City city) {
        city.setDefence(city.getDefence() + defenseImprovePoint);
    }
}
