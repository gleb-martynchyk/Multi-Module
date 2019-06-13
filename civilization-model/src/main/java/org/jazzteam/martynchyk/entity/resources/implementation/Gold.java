package org.jazzteam.martynchyk.entity.resources.implementation;

import org.jazzteam.martynchyk.entity.resources.Resource;

import javax.persistence.Entity;

@Entity
public class Gold extends Resource {

    public Gold() {
    }

    public Gold(int amount) {
        super(amount);
    }
}
