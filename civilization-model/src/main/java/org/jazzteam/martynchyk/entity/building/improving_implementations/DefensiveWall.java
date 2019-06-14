package org.jazzteam.martynchyk.entity.building.improving_implementations;

import lombok.Data;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.building.ImprovingEntity;

import javax.persistence.Entity;

@Data
@Entity
public class DefensiveWall extends ImprovingEntity {

    private static final int defenseImprovePoint = 10;

    public DefensiveWall() {
        super(60);
    }

    @Override
    public void afterRemoving(City city) {
        city.setDefence(city.getDefence() - defenseImprovePoint);
    }

    @Override
    public void improveAttribute(City city) {
        city.setDefence(city.getDefence() + defenseImprovePoint);
    }
}
