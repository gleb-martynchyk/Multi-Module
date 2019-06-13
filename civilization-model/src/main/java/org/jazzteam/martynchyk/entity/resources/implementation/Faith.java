package org.jazzteam.martynchyk.entity.resources.implementation;

import org.jazzteam.martynchyk.entity.resources.Resource;

import javax.persistence.Entity;

@Entity
public class Faith extends Resource {
    public Faith() {
    }

    public Faith(int amount) {
        super(amount);
    }
}
