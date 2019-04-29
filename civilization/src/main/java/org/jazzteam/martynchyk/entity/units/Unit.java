package org.jazzteam.martynchyk.entity.units;

import lombok.Data;
import org.jazzteam.martynchyk.entity.ResourceType;

@Data
public abstract class Unit {
    private int costInGold;
    private int costInResources;
    private ResourceType resourceType;
    private int healthPoint;
    private int movement;
}