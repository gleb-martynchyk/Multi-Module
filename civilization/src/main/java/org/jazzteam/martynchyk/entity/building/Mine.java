package org.jazzteam.martynchyk.entity.building;

import lombok.Getter;
import org.jazzteam.martynchyk.entity.City;

@Getter
public class Mine extends Building implements Producing {
    private static final int productionQuantity = 5;

    public Mine() {
    }

    @Override
    public void produceResource(City city) {
        city.getProduction().setAmount(city.getProduction().getAmount() + productionQuantity);
    }
}
