package org.jazzteam.martynchyk.entity.building;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "improving_building")
public abstract class ImprovingEntity extends Building implements Improving {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public ImprovingEntity(int productionCost) {
        super(productionCost);
    }
}
