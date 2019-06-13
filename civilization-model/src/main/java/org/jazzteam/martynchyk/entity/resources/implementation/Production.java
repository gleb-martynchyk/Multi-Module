package org.jazzteam.martynchyk.entity.resources.implementation;

import org.jazzteam.martynchyk.entity.resources.Resource;

import javax.persistence.Entity;

@Entity
public class Production extends Resource {

    public Production() {
    }

    public Production(int amount) {
        super(amount);
    }
}
