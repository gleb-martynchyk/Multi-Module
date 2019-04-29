package org.jazzteam.martynchyk.entity.units.religious;

import lombok.Data;
import org.jazzteam.martynchyk.entity.ReligionType;
import org.jazzteam.martynchyk.entity.units.Unit;

@Data
public abstract class Religious extends Unit {
    private ReligionType religion;
    private int religiousStrength;
}
