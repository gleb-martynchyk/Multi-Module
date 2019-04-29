package org.jazzteam.martynchyk.entity.units;

import lombok.Data;
import org.jazzteam.martynchyk.entity.City;

import javax.persistence.Entity;

@Data
@Entity
public class Settler extends Unit {
    public City baseCity() {
        return new City();
    }
}
