package org.jazzteam.martynchyk.entity.building.improving_implementations;

import lombok.Data;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.building.ImprovingEntity;

import javax.persistence.Entity;

@Data
@Entity
public class Barrack extends ImprovingEntity {

    private static final int strengthImprovePoint = 10;

    public Barrack() {
        super(50);
    }

    @Override
    public void afterRemoving(City city) {
        city.setStrength(city.getStrength() - strengthImprovePoint);
    }

    @Override
    public void improveAttribute(City city) {
        city.setStrength(city.getStrength() + strengthImprovePoint);
    }
}
