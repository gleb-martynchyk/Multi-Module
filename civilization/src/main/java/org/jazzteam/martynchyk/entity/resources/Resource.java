package org.jazzteam.martynchyk.entity.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Resource {
    private int amount;

    public Resource(int amount) {
        this.amount = amount;
    }
}
