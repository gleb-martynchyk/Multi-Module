package org.jazzteam.martynchyk.entity.building.providing_implementations;

import lombok.Getter;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.building.ProducingEntity;

import javax.persistence.Entity;

@Getter
@Entity
public class Mine extends ProducingEntity {
    private static final int productionQuantity = 5;

    public Mine() {
        super(0, 50);
    }

    @Override
    public void produceResource(City city) {
        city.getProduction().setAmount(city.getProduction().getAmount() + productionQuantity);
    }
}
