package org.jazzteam.martynchyk.entity.building;

import lombok.Getter;
import org.jazzteam.martynchyk.entity.City;

@Getter
public class Campus extends Building implements Producing {

    private static final int productionQuantity = 5;

    public Campus() {
    }

    @Override
    public void produceResource(City city) {
        city.getCivilization().setScience(city.getCivilization().getScience() + productionQuantity);
    }
}
