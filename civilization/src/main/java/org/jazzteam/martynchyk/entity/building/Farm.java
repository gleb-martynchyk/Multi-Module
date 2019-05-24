package org.jazzteam.martynchyk.entity.building;

import lombok.Getter;
import org.jazzteam.martynchyk.entity.City;

@Getter
public class Farm extends Building implements Producing {

    private static final int productionQuantity = 5;

    public Farm() {
    }

    @Override
    public void produceResource(City city) {
        city.setFood(city.getFood() + productionQuantity);
    }
}
