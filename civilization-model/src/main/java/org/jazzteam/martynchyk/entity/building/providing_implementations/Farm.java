package org.jazzteam.martynchyk.entity.building.providing_implementations;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.building.ProducingEntity;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Farm extends ProducingEntity {

    public static final int productionQuantity = 5;

    public Farm() {
        super(10, 40);
    }

    @Override
    public void produceResource(City city) {
        city.getFood().setAmount(city.getFood().getAmount() + productionQuantity);
    }
}
