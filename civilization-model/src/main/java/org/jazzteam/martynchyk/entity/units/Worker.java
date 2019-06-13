package org.jazzteam.martynchyk.entity.units;

import lombok.Data;
import org.jazzteam.martynchyk.entity.enums.ResourceType;

import javax.persistence.Entity;

@Data
@Entity
public class Worker extends Unit {
    private int productionProvide;

    public Worker() {
        super(10, 20, ResourceType.PRODUCTION, 0);
        productionProvide = 1;
    }
}
