package com.jazzteam.martynchyk.factory.creator;

import com.jazzteam.martynchyk.factory.units.Archer;
import com.jazzteam.martynchyk.factory.units.Unit;

public class ArcherCreator extends UnitsCreator {
    @Override
    Unit createUnit() {
        return new Archer();
    }
}
