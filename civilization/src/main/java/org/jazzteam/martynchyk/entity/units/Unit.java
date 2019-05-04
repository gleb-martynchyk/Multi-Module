package org.jazzteam.martynchyk.entity.units;

import lombok.Data;
import org.jazzteam.martynchyk.entity.enums.ResourceType;

import javax.persistence.*;

@Data
@Entity
public abstract class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int costInGold;
    private int costInResources;
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;
    private double healthPoint;
    private int movement;

    public Unit() {
    }

    public Unit(int costInGold, int costInResources, ResourceType resourceType, int movement) {
        this.costInGold = costInGold;
        this.costInResources = costInResources;
        this.resourceType = resourceType;
        this.healthPoint = 100;
        this.movement = movement;
    }
}