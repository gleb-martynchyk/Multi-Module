package org.jazzteam.martynchyk.entity.units.military;

import lombok.Data;
import org.jazzteam.martynchyk.entity.Combat;
import org.jazzteam.martynchyk.entity.units.Unit;

@Data
public abstract class BaseWarrior extends Unit implements Combat {
    private int range;
    private int rangedStrength;
    private int strength;
}
