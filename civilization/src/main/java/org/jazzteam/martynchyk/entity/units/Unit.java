package org.jazzteam.martynchyk.entity.units;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.City;
import org.jazzteam.martynchyk.entity.enums.ResourceType;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
public abstract class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Transient
    private City city;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return id == unit.id &&
                costInGold == unit.costInGold &&
                costInResources == unit.costInResources &&
                Double.compare(unit.healthPoint, healthPoint) == 0 &&
                movement == unit.movement &&
                resourceType == unit.resourceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, costInGold, costInResources, resourceType, healthPoint, movement);
    }
}