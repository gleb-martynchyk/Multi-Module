package org.jazzteam.martynchyk.entity.building.providing_implementations;

import lombok.Getter;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.building.Building;
import org.jazzteam.martynchyk.entity.building.Producing;
import org.jazzteam.martynchyk.entity.building.ProducingEntity;

import javax.persistence.Entity;

@Getter
@Entity
public class Temple extends ProducingEntity {
    private static final int productionQuantity = 5;

    public Temple() {
        super(2, 3);
    }

    @Override
    public void produceResource(City city) {
        city.getCivilization().setFaith(city.getCivilization().getFaith() + productionQuantity);
    }
}
