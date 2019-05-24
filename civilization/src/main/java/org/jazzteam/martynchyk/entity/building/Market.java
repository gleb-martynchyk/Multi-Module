package org.jazzteam.martynchyk.entity.building;

import lombok.Getter;
import org.jazzteam.martynchyk.entity.City;

@Getter
public class Market extends Building implements Producing {
    private static final int productionQuantity = 5;

    public Market() {
    }

    @Override
    public void produceResource(City city) {
        city.getCivilization().setGold(city.getCivilization().getGold() + productionQuantity);
    }
}
