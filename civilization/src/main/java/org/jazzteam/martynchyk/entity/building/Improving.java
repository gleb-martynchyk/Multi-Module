package org.jazzteam.martynchyk.entity.building;

import org.jazzteam.martynchyk.entity.City;

public interface Improving {
    void afterRemoving(City city);

    void improveAttribute(City city);
}
