package org.jazzteam.martynchyk.entity.building;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "producing_building")
public abstract class ProducingEntity extends ResidentialBuilding implements Producing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public ProducingEntity(int productionCost, int slots) {
        super(productionCost, slots);
    }
}
