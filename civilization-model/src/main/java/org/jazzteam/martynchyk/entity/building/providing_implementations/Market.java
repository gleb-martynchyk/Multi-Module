package org.jazzteam.martynchyk.entity.building.providing_implementations;

import lombok.Getter;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.building.ProducingEntity;

import javax.persistence.Entity;

@Getter
@Entity
public class Market extends ProducingEntity {
    private static final int productionQuantity = 5;

    public Market() {
        super(0,70);
    }

    @Override
    public void produceResource(City city) {
        city.getCivilization().setGold(city.getCivilization().getGold() + productionQuantity);
    }
}
