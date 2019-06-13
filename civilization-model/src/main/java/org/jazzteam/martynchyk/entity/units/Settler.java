package org.jazzteam.martynchyk.entity.units;

import lombok.Data;
import org.jazzteam.martynchyk.entity.enums.ResourceType;

import javax.persistence.Entity;

@Data
@Entity
public class Settler extends Unit {
    public Settler() {
        super(5, 0, ResourceType.PRODUCTION, 0);
    }
}
