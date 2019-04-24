package org.jazzteam.martynchyk.entity.units;

import lombok.Data;
import org.jazzteam.martynchyk.entity.City;

@Data
public class Settier extends Unit {
    public City baseCity() {
        return new City();
    }
}
