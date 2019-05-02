package org.jazzteam.martynchyk.entity.units;

import lombok.Data;
import org.jazzteam.martynchyk.entity.enums.ResourceType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public abstract class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int costInGold;
    private int costInResources;
    private ResourceType resourceType;
    private int healthPoint;
    private int movement;
}