package org.jazzteam.martynchyk.entity.resources;

import lombok.Getter;
import lombok.Setter;
import org.jazzteam.martynchyk.entity.City;
import org.sonatype.inject.EagerSingleton;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "resource_tb")
public abstract class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int amount;
    protected final static int MIN = 5;

    public Resource() {
    }

    public Resource(int amount) {
        this.amount = amount;
    }

    public boolean isInAbundance(City city) {
        return getExcess(city) > 0;
    }

    public int getExcess(City city) {
        return getAmount() - MIN;
    }

    public boolean isEmpty() {
        return getAmount() < MIN;
    }
}
