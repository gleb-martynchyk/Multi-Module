package org.jazzteam.martynchyk.entity.building;

import lombok.Getter;
import org.jazzteam.martynchyk.entity.City;

@Getter
public class Temple extends Building implements Producing {
    private static final int productionQuantity = 5;

    public Temple() {
    }

    @Override
    public void produceResource(City city) {
        city.getCivilization().setFaith(city.getCivilization().getFaith() + productionQuantity);
    }
}
