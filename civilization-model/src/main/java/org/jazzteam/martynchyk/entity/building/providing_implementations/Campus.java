package org.jazzteam.martynchyk.entity.building.providing_implementations;

import lombok.Getter;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.building.ProducingEntity;

import javax.persistence.Entity;

@Getter
@Entity
public class Campus extends ProducingEntity {

    private static final int productionQuantity = 5;

    public Campus() {
        super(0, 55);
    }

    @Override
    public void produceResource(City city) {
        city.getCivilization().setScience(city.getCivilization().getScience() + productionQuantity);
    }
}
