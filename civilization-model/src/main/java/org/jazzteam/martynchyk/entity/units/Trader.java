package org.jazzteam.martynchyk.entity.units;

import lombok.Data;
import org.jazzteam.martynchyk.entity.enums.ResourceType;

import javax.persistence.Entity;

@Data
@Entity
public class Trader extends Unit {
    public Trader() {
        super(10, 20, ResourceType.PRODUCTION, 0);
    }
}
