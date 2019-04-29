package org.jazzteam.martynchyk.entity.units;

import lombok.Data;
import org.jazzteam.martynchyk.entity.ResourceType;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public abstract class Unit {
    @Id
    private long id;
    private int costInGold;
    private int costInResources;
    private ResourceType resourceType;
    private int healthPoint;
    private int movement;
}