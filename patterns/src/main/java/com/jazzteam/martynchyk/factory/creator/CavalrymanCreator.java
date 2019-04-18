package com.jazzteam.martynchyk.factory.creator;

import com.jazzteam.martynchyk.factory.units.Cavalryman;
import com.jazzteam.martynchyk.factory.units.Unit;

public class CavalrymanCreator extends UnitsCreator {
    @Override
    Unit createUnit() {
        return new Cavalryman();
    }
}
